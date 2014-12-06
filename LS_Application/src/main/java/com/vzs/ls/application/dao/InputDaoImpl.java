package com.vzs.ls.application.dao;

import com.vzs.common.util.poi.reader.BPoiReaderTemplate;
import com.vzs.ls.application.input.pojo.InventoryRecipeTransfer.InventoryRecipeTransferWorkbook;
import com.vzs.ls.application.input.pojo.MaterialMaintain.MaterialMaintainWorkbook;
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
		BPoiReaderTemplate<MaterialMaintainWorkbook> bPoiReaderTemplate = new BPoiReaderTemplate<MaterialMaintainWorkbook>(filePath,MaterialMaintainWorkbook.class);
		bPoiReaderTemplate.execute();
		return bPoiReaderTemplate.getBWorkbook();
	}

	public InventoryRecipeTransferWorkbook getInventoryRecipeTransferWorkbook(String filePath){
		return getWorkbook(filePath,InventoryRecipeTransferWorkbook.class);
	}
}
