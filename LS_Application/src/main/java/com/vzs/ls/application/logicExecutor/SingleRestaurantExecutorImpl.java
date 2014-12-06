package com.vzs.ls.application.logicExecutor;

import com.vzs.ls.application.dao.InputDaoImpl;
import com.vzs.ls.application.input.pojo.DishesSellerStatistic.DishesSellerStatisticWorkbook;
import com.vzs.ls.application.input.pojo.InputContext;
import com.vzs.ls.application.input.pojo.InventoryRecipeTransfer.InventoryRecipeTransferWorkbook;
import com.vzs.ls.application.input.pojo.MaterialMaintain.MaterialMaintainWorkbook;
import com.vzs.ls.application.input.pojo.WeeklyInventory.WeeklyInventoryWorkbook;

/**
 * Created by ben.yao on 12/6/2014.
 */
public class SingleRestaurantExecutorImpl {
	InputDaoImpl inputDao = new InputDaoImpl();
	InputContext inputContext;
	MaterialMaintainWorkbook materialMaintainWorkbook;
	InventoryRecipeTransferWorkbook inventoryRecipeTransferWorkbook;

	public SingleRestaurantExecutorImpl(InputContext inputContext){
		this.inputContext=inputContext;
	}

	public void execute(){
		materialMaintainWorkbook = inputDao.getMaterialMaintainWorkbook(inputContext.getMaterialMaintian());
		System.out.println(materialMaintainWorkbook);
		inventoryRecipeTransferWorkbook = inputDao.getInventoryRecipeTransferWorkbook(inputContext.getInventoryRecipTransfer());
		System.out.println(inventoryRecipeTransferWorkbook);
		DishesSellerStatisticWorkbook dishesSellerStatisticWorkbook = inputDao.getDishesSellerStatisticWorkbook(inputContext.getDishesSaleStatistic());
		System.out.println(dishesSellerStatisticWorkbook);
		WeeklyInventoryWorkbook weeklyInventoryWorkbook = inputDao.getWorkbook(inputContext.getWeeklyInventory(), WeeklyInventoryWorkbook.class);
		System.out.println(weeklyInventoryWorkbook);
	}


}
