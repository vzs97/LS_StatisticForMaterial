package com.vzs.ls.application.dao;

import com.vzs.common.util.poi.reader.BPoiReaderTemplate;
import com.vzs.ls.application.input.pojo.DishesSellerStatistic.DishesSellerStatisticWorkbook;
import com.vzs.ls.application.input.pojo.InventoryRecipeTransfer.InventoryRecipeTransferWorkbook;
import com.vzs.ls.application.input.pojo.MaterialMaintain.MaterialMaintainWorkbook;
import com.vzs.ls.application.input.pojo.WeeklyInventory.WeeklyInventoryWorkbook;
import lombok.NoArgsConstructor;

/**
 * Created by ben.yao on 12/6/2014.
 */
@NoArgsConstructor
public class InputDaoImpl {
	public <T> T getWorkbook(String filepath,Class<T> clazz){
		BPoiReaderTemplate<T> bPoiReaderTemplate = new BPoiReaderTemplate<T>(filepath,clazz);
		bPoiReaderTemplate.execute();
		return bPoiReaderTemplate.getBWorkbook();
	}
	public MaterialMaintainWorkbook getMaterialMaintainWorkbook(String filePath){
		return getWorkbook(filePath,MaterialMaintainWorkbook.class);
	}

	public InventoryRecipeTransferWorkbook getInventoryRecipeTransferWorkbook(String filePath){
		return getWorkbook(filePath,InventoryRecipeTransferWorkbook.class);
	}
	public DishesSellerStatisticWorkbook getDishesSellerStatisticWorkbook(String filePath){
		return getWorkbook(filePath,DishesSellerStatisticWorkbook.class);
	}
	public WeeklyInventoryWorkbook getWeeklyInventoryWorkbook(String filePath){
		return getWorkbook(filePath,WeeklyInventoryWorkbook.class);
	}
}
