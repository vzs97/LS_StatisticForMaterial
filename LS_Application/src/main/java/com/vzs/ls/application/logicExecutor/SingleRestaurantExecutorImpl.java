package com.vzs.ls.application.logicExecutor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vzs.ls.application.dao.InputDaoImpl;
import com.vzs.ls.application.input.pojo.DishesSellerStatistic.DishesSellerStatisticWorkbook;
import com.vzs.ls.application.input.pojo.InputContext;
import com.vzs.ls.application.input.pojo.InventoryRecipeTransfer.InventoryRecipeTransferRow;
import com.vzs.ls.application.input.pojo.InventoryRecipeTransfer.InventoryRecipeTransferWorkbook;
import com.vzs.ls.application.input.pojo.LSRecipe.LSRecipeWorkbook;
import com.vzs.ls.application.input.pojo.MaterialMaintain.MaterialMaintainWorkbook;
import com.vzs.ls.application.input.pojo.MaterialMaintain.MaterialRow;
import com.vzs.ls.application.input.pojo.ResturantMaintain.ResturantMaintainRow;
import com.vzs.ls.application.input.pojo.ResturantMaintain.ResturantMaintainWorkbook;
import com.vzs.ls.application.input.pojo.WeeklyInventory.WeeklyInventoryWorkbook;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantRow;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantSheet;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantWookbook;
import com.vzs.ls.application.utils.NormalUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.BReflectHelper;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Map;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
@NoArgsConstructor
public class SingleRestaurantExecutorImpl {
	InputDaoImpl inputDao = new InputDaoImpl();
	InputContext inputContext;
	MaterialMaintainWorkbook materialMaintainWorkbook;
	InventoryRecipeTransferWorkbook inventoryRecipeTransferWorkbook;
	LSRecipeWorkbook lsRecipeWorkbook;
    ResturantMaintainWorkbook resturantMaintainWorkbook;
    Map<String,DishesSellerStatisticWorkbook> resturantNameToWorkbook = Maps.newHashMap();

	public SingleRestaurantExecutorImpl(InputContext inputContext){
		this.inputContext=inputContext;
	}

	public void execute(){
		init();
        for (ResturantMaintainRow resturantMaintainRow : resturantMaintainWorkbook.getResturantMaintainSheet().getResturantMaintainRowList()) {
            List<SingleRestaurantRow> singleResturuantRowList = materialMaintainTableInit();
            DishesSellerStatisticWorkbook dishesSellerStatisticWorkbook =getResturantNameToWorkbook().get(resturantMaintainRow.getResturantName());
            if(dishesSellerStatisticWorkbook == null){
                System.out.println("找不到对应的菜品销售表:"+resturantMaintainRow.getResturantName() +":无视该餐厅");
                continue;
            }
            initInventory(singleResturuantRowList);
            loopSingleResturantRow(singleResturuantRowList, new TheoryCousumptionCall(this, resturantMaintainRow));
            loopSingleResturantRow(singleResturuantRowList, new RealConsumptionCall(this, resturantMaintainRow));
            loopSingleResturantRow(singleResturuantRowList, new DiffCall());
            loopSingleResturantRow(singleResturuantRowList, new DiffMoneyCall(this, resturantMaintainRow));
            loopSingleResturantRow(singleResturuantRowList,new GetRateCall());


            SingleRestaurantWookbook singleRestaurantWookbook = new SingleRestaurantWookbook();
            SingleRestaurantSheet singleRestaurantSheet = new SingleRestaurantSheet();
            singleRestaurantSheet.setSingleRestaurantRowList(singleResturuantRowList);
            singleRestaurantWookbook.setSingleRestaurantSheet(singleRestaurantSheet);
            inputDao.writeWorkbook(inputContext.getSingleRestuarntFolder(),resturantMaintainRow.getResturantName()+".xls",null,singleRestaurantWookbook);
            System.out.println(singleResturuantRowList);

        }

	}


    private void loopSingleResturantRow(List<SingleRestaurantRow> singleResturuantRowList,SingleRestaurantRowCall call){
        for (SingleRestaurantRow singleRestaurantRow : singleResturuantRowList) {
            call.call(singleRestaurantRow);
        }
    }

	/**
	 * init Inventory Unit Cell
	 * @param singleResturuantRowList
	 */
	private void initInventory(List<SingleRestaurantRow> singleResturuantRowList){
		for (SingleRestaurantRow singleRestaurantRow : singleResturuantRowList) {
			String materialNo = singleRestaurantRow.getMaterialNo();
			InventoryRecipeTransferRow inventoryRecipeTransferRow = inventoryRecipeTransferWorkbook.getIdToRow().get(materialNo);
			if(inventoryRecipeTransferRow != null){
				singleRestaurantRow.setInventoryUnit(inventoryRecipeTransferRow.getInventoryUnit());
			}
		}
	}

	private List<SingleRestaurantRow> materialMaintainTableInit() {
		List<SingleRestaurantRow> singleRestuarntRowList = Lists.newArrayList();
		//init materialTable to single restaurant
		for (MaterialRow materialRow : materialMaintainWorkbook.getMaterialSheet().getLsMaterialRowList()) {
			boolean isReviewByWeek = materialRow.isReviewByWeek();
			if(isReviewByWeek){
				SingleRestaurantRow row = BReflectHelper.newInstance(SingleRestaurantRow.class);
				row.setClassification(materialRow.getClassification());
				row.setMaterialNo(materialRow.getProductSeriesId());
				row.setName(materialRow.getMaterialName());
				row.setTargetValue(materialRow.getTargetValue());
				singleRestuarntRowList.add(row);
			}
		}
		return singleRestuarntRowList;
	}

	/**
	 * init load once xls
	 */
	private void init() {
		materialMaintainWorkbook =  inputDao.getWorkbook(inputContext.getMaterialMaintian(), MaterialMaintainWorkbook.class);
		inventoryRecipeTransferWorkbook = inputDao.getWorkbook(inputContext.getInventoryRecipTransfer(), InventoryRecipeTransferWorkbook.class);
		inventoryRecipeTransferWorkbook.init();
		lsRecipeWorkbook = inputDao.getWorkbook(inputContext.getRecipe(), LSRecipeWorkbook.class);
		lsRecipeWorkbook.init();
        resturantMaintainWorkbook = inputDao.getWorkbook(inputContext.getResturantMaintain(),ResturantMaintainWorkbook.class);
        initDishesSeller();
	}

    private void initDishesSeller(){
        File directory = new File(inputContext.getDishesSaleStatistic());
        if(!directory.isDirectory()){
            return;
        }
        FileFilter fileFilter = new FileFilter(){

            @Override
            public boolean accept(File file) {
                if(file.isFile() && (file.getName().endsWith("xls") || file.getName().endsWith("xlsx")) && !file.getName().startsWith("~$")){
                    return true;
                }
                return false;
            }
        };
        for (File file : directory.listFiles(fileFilter)) {
            String fileName = file.getName();

            String restruantName = NormalUtil.extractResturantName(fileName);
//            System.out.println(fileName);
            DishesSellerStatisticWorkbook dishesSellerStatisticWorkbook = inputDao.getWorkbook(file.getAbsolutePath(),DishesSellerStatisticWorkbook.class);
            dishesSellerStatisticWorkbook.getDishesSellerStatisticSheet().initDishesMap();
            resturantNameToWorkbook.put(restruantName,dishesSellerStatisticWorkbook);
        }
        ;
    }

}
