package com.vzs.ls.application.logicExecutor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.vzs.common.util.dao.BWorkbookDaoImpl;
import com.vzs.common.util.log.SingleThreadLogUtil;
import com.vzs.ls.application.input.pojo.DishesSellerStatistic.DishesSellerStatisticWorkbook;
import com.vzs.ls.application.input.pojo.InputContext;
import com.vzs.ls.application.input.pojo.InventoryRecipeTransfer.InventoryRecipeTransferRow;
import com.vzs.ls.application.input.pojo.InventoryRecipeTransfer.InventoryRecipeTransferWorkbook;
import com.vzs.ls.application.input.pojo.LSRecipe.LSRecipeWorkbook;
import com.vzs.ls.application.input.pojo.MaterialMaintain.MaterialMaintainWorkbook;
import com.vzs.ls.application.input.pojo.MaterialMaintain.MaterialRow;
import com.vzs.ls.application.input.pojo.ProductIDReference.ProductIDReferenceRow;
import com.vzs.ls.application.input.pojo.ProductIDReference.ProductIDReferenceWorkbook;
import com.vzs.ls.application.input.pojo.ResturantMaintain.ResturantMaintainRow;
import com.vzs.ls.application.input.pojo.ResturantMaintain.ResturantMaintainWorkbook;
import com.vzs.ls.application.input.pojo.WeeklyInventory.WeeklyInventoryRow;
import com.vzs.ls.application.input.pojo.WeeklyInventory.WeeklyInventoryWorkbook;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantRow;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantSheet;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantWookbook;
import com.vzs.ls.application.utils.NormalUtil;
import javafx.util.Pair;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.BReflectHelper;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by ben.yao on 12/6/2014.
 */
@Data
@NoArgsConstructor
public class SingleRestaurantExecutorImpl {
    BWorkbookDaoImpl inputDao = new BWorkbookDaoImpl();
	InputContext inputContext;
	MaterialMaintainWorkbook materialMaintainWorkbook;
	InventoryRecipeTransferWorkbook inventoryRecipeTransferWorkbook;
	LSRecipeWorkbook lsRecipeWorkbook;
    ResturantMaintainWorkbook resturantMaintainWorkbook;
    Map<String,DishesSellerStatisticWorkbook> resturantNameToWorkbook = Maps.newHashMap();
    Map<String,WeeklyInventoryWorkbook> resturanNoToWorkbook = Maps.newHashMap();

    ProductIDReferenceWorkbook productIDReferenceWorkbook;

	public SingleRestaurantExecutorImpl(InputContext inputContext){
		this.inputContext=inputContext;
	}

//    public <T> List<T> getIfHave(String resturantNo,String key,Map<String,T> maps){
//        return getIfHave(resturantNo,key,maps,true);
//    }
    public <T> Pair<GetIfHaveEnum,List<T>> getIfHave(String resturantNo,String key,Map<String,T> maps, boolean isDeep){
        List<T> reList = Lists.newArrayList();
        T re = maps.get(key);
        if(re != null){
            reList.add(re);
            return new Pair(GetIfHaveEnum.GOOD,reList);
        }else if(!isDeep){
            return null;
        }
        WeeklyInventoryWorkbook weeklyInventoryWorkbook = resturanNoToWorkbook.get(resturantNo);
        if(weeklyInventoryWorkbook == null){
            return null;
        }
        int moreThan0=0;
        int equals0=0;
        int nullValue=0;
        T moreThan0T = null;
        T equals0T = null;
        ProductIDReferenceWorkbook productIDReferenceWorkbook = getProductIDReferenceWorkbook();
        Multimap<String, ProductIDReferenceRow> multimap = productIDReferenceWorkbook.getProductIDReferenceSheet().getMultimap();
        for (ProductIDReferenceRow productIDReferenceRow : multimap.get(key)) {
            WeeklyInventoryRow weeklyInventoryRow = weeklyInventoryWorkbook.getWeeklyInventorySheet().getMaterialNoToRow().get(productIDReferenceRow.getJdeCode());
            if(weeklyInventoryRow == null){
                continue;
            }
            re = maps.get(productIDReferenceRow.getJdeCode());
            if(re != null){
                Double monthlySale = weeklyInventoryRow.getMonthlySale();
                if(monthlySale == null){
                    nullValue++;
                }else if(monthlySale.equals(0D)){
                    equals0++;
                    equals0T = re;
                }else if(monthlySale > 0D){
                    moreThan0++;
                    moreThan0T=re;
                }

                reList.add(re);
            }

        }

        if(moreThan0 == 1){
            reList.add(0,moreThan0T);
            return new Pair(GetIfHaveEnum.GOOD,reList);
        }else if(moreThan0 > 1){
            return new Pair(GetIfHaveEnum.BAD,reList);
        }else if(equals0 == 1){
            reList.add(0,equals0T);
            return new Pair(GetIfHaveEnum.GOOD,reList);
        }else if(equals0 > 1){
            return new Pair(GetIfHaveEnum.BAD,reList);
        }
        return  new Pair(GetIfHaveEnum.BAD,reList);
    }

	public void execute(){
		init();
        for (ResturantMaintainRow resturantMaintainRow : resturantMaintainWorkbook.getResturantMaintainSheet().getResturantMaintainRowList()) {
            List<SingleRestaurantRow> singleResturuantRowList = materialMaintainTableInit();
            DishesSellerStatisticWorkbook dishesSellerStatisticWorkbook =getResturantNameToWorkbook().get(resturantMaintainRow.getResturantName());
            if(dishesSellerStatisticWorkbook == null){
                SingleThreadLogUtil.log("找不到对应的菜品销售表:" + resturantMaintainRow.getResturantName() + ":无视该餐厅");
                continue;
            }
            initInventory(resturantMaintainRow,singleResturuantRowList);
            loopSingleResturantRow(singleResturuantRowList, new TheoryCousumptionCall(this, resturantMaintainRow));
            loopSingleResturantRow(singleResturuantRowList, new RealConsumptionCall(this, resturantMaintainRow));
            loopSingleResturantRow(singleResturuantRowList, new DiffCall());
            loopSingleResturantRow(singleResturuantRowList, new DiffMoneyCall(this, resturantMaintainRow));
            loopSingleResturantRow(singleResturuantRowList,new GetRateCall());
            loopSingleResturantRow(singleResturuantRowList,new DumpCall());


            SingleRestaurantWookbook singleRestaurantWookbook = new SingleRestaurantWookbook();
            SingleRestaurantSheet singleRestaurantSheet = new SingleRestaurantSheet();
            singleRestaurantSheet.setSingleRestaurantRowList(singleResturuantRowList);
            singleRestaurantWookbook.setSingleRestaurantSheet(singleRestaurantSheet);
            inputDao.writeWorkbook(inputContext.getSingleRestuarntFolder(),resturantMaintainRow.getResturantName()+".xls",inputContext.getSingleResturantTemplate(),singleRestaurantWookbook);
//            SingleThreadLogUtil.log(singleResturuantRowList);

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
	private void initInventory(ResturantMaintainRow resturantMaintainRow,List<SingleRestaurantRow> singleResturuantRowList){
		for (SingleRestaurantRow singleRestaurantRow : singleResturuantRowList) {
			String materialNo = singleRestaurantRow.getMaterialNo();
            Map<String, InventoryRecipeTransferRow> idToRow = inventoryRecipeTransferWorkbook.getIdToRow();
            Pair<GetIfHaveEnum, List<InventoryRecipeTransferRow>> ifHave = getIfHave(resturantMaintainRow.getResturantNo(), materialNo, idToRow, true);
            GetIfHaveEnum key = ifHave.getKey();
            List<InventoryRecipeTransferRow> value = ifHave.getValue();


            if(GetIfHaveEnum.GOOD.equals(key)){
                InventoryRecipeTransferRow inventoryRecipeTransferRow = value.get(0);
                singleRestaurantRow.setInventoryUnit(inventoryRecipeTransferRow.getInventoryUnit());
                singleRestaurantRow.setTempJDECode(inventoryRecipeTransferRow.getJdeCode());
                singleRestaurantRow.setJdeCodeComments(inventoryRecipeTransferRow.getJdeCode());
			}else{
                StringBuilder sb = new StringBuilder();
                if(!value.isEmpty()){
                    for (InventoryRecipeTransferRow inventoryRecipeTransferRow : value) {
                        sb.append(inventoryRecipeTransferRow.getJdeCode()).append(",");
                    }
                    singleRestaurantRow.setJdeCodeComments(sb.toString());
                }
                SingleThreadLogUtil.log("Can't find Inventory Unit for " + materialNo + " with jdecodes:"+sb.toString());
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
        initWeeklyInventoryWorkbook();
        productIDReferenceWorkbook = inputDao.getWorkbook(inputContext.getProductIdRefrence(),ProductIDReferenceWorkbook.class);
        productIDReferenceWorkbook.getProductIDReferenceSheet().initRowList();
	}

    private void initWeeklyInventoryWorkbook(){
        //resturanNoToWorkbook
        File directory = new File(inputContext.getWeeklyInventory());
        if(!directory.isDirectory()){
            return;
        }
        for (File file : directory.listFiles(NormalUtil.xlsFileFilter)) {
            String fileName = file.getName();
            WeeklyInventoryWorkbook dishesSellerStatisticWorkbook = inputDao.getWorkbook(file.getAbsolutePath(),WeeklyInventoryWorkbook.class);
            dishesSellerStatisticWorkbook.getWeeklyInventorySheet().initInventoryMap();
            resturanNoToWorkbook.put(NormalUtil.extractPIITRestruanNo(fileName),dishesSellerStatisticWorkbook);
        }

    }

    private void initDishesSeller(){
        File directory = new File(inputContext.getDishesSaleStatistic());
        if(!directory.isDirectory()){
            return;
        }
        for (File file : directory.listFiles(NormalUtil.xlsFileFilter)) {
            String fileName = file.getName();

            String restruantName = NormalUtil.extractResturantName(fileName);
//            SingleThreadLogUtil.log(fileName);
            DishesSellerStatisticWorkbook dishesSellerStatisticWorkbook = inputDao.getWorkbook(file.getAbsolutePath(),DishesSellerStatisticWorkbook.class);
            dishesSellerStatisticWorkbook.getDishesSellerStatisticSheet().initDishesMap();
            resturantNameToWorkbook.put(restruantName,dishesSellerStatisticWorkbook);
        }
        ;
    }

}
