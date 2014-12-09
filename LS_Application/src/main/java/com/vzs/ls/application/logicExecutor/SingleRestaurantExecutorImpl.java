package com.vzs.ls.application.logicExecutor;

import com.google.common.collect.Lists;
import com.vzs.ls.application.dao.InputDaoImpl;
import com.vzs.ls.application.input.pojo.DishesSellerStatistic.DishesSellerStatisticWorkbook;
import com.vzs.ls.application.input.pojo.InputContext;
import com.vzs.ls.application.input.pojo.InventoryRecipeTransfer.InventoryRecipeTransferRow;
import com.vzs.ls.application.input.pojo.InventoryRecipeTransfer.InventoryRecipeTransferWorkbook;
import com.vzs.ls.application.input.pojo.LSRecipe.LSRecipeWorkbook;
import com.vzs.ls.application.input.pojo.MaterialMaintain.MaterialMaintainWorkbook;
import com.vzs.ls.application.input.pojo.MaterialMaintain.MaterialRow;
import com.vzs.ls.application.input.pojo.WeeklyInventory.WeeklyInventoryWorkbook;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantRow;
import utils.BReflectHelper;

import java.util.List;

/**
 * Created by ben.yao on 12/6/2014.
 */
public class SingleRestaurantExecutorImpl {
	InputDaoImpl inputDao = new InputDaoImpl();
	InputContext inputContext;
	MaterialMaintainWorkbook materialMaintainWorkbook;
	InventoryRecipeTransferWorkbook inventoryRecipeTransferWorkbook;
	LSRecipeWorkbook lsRecipeWorkbook;

	public SingleRestaurantExecutorImpl(InputContext inputContext){
		this.inputContext=inputContext;
	}

	public void executeExample(){
		materialMaintainWorkbook = inputDao.getMaterialMaintainWorkbook(inputContext.getMaterialMaintian());
		System.out.println(materialMaintainWorkbook);
		inventoryRecipeTransferWorkbook = inputDao.getInventoryRecipeTransferWorkbook(inputContext.getInventoryRecipTransfer());
		System.out.println(inventoryRecipeTransferWorkbook);
		DishesSellerStatisticWorkbook dishesSellerStatisticWorkbook = inputDao.getDishesSellerStatisticWorkbook(inputContext.getDishesSaleStatistic());
		System.out.println(dishesSellerStatisticWorkbook);
		WeeklyInventoryWorkbook weeklyInventoryWorkbook = inputDao.getWorkbook(inputContext.getWeeklyInventory(), WeeklyInventoryWorkbook.class);
		System.out.println(weeklyInventoryWorkbook);
	}
	public void execute(){
		init();
		List<SingleRestaurantRow> singleResturuantRowList = materialMaintainTableInit();
		initInventory(singleResturuantRowList);
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
		System.out.println(lsRecipeWorkbook.getExceptPotRecipe());
	}

}
