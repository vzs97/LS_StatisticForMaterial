package com.vzs.ls.application.dao;

import com.vzs.common.util.poi.reader.BPoiReaderTemplate;
import com.vzs.common.util.poi.writer.BPoiWriterTemplate;
import com.vzs.ls.application.input.pojo.DishesSellerStatistic.DishesSellerStatisticWorkbook;
import com.vzs.ls.application.input.pojo.InventoryRecipeTransfer.InventoryRecipeTransferWorkbook;
import com.vzs.ls.application.input.pojo.MaterialMaintain.MaterialMaintainWorkbook;
import com.vzs.ls.application.input.pojo.WeeklyInventory.WeeklyInventoryWorkbook;
import lombok.NoArgsConstructor;
import utils.BWorkbookUtil;

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
    public <T> void writeWorkbook(String filePath,String fileName,String templatePath,T workkbook){
        BWorkbookUtil.mkdirsIfNotExist(filePath);
        BPoiWriterTemplate<T> bPoiWriterTemplate = new BPoiWriterTemplate(filePath+fileName,workkbook);
        bPoiWriterTemplate.execute();;
    }
}
