package com.vzs.common.util.poi.reader;

import com.vzs.common.util.poi.pojo.BSheet;
import lombok.Data;

/**
 * Created by vzs on 2014/12/3.
 */
public class SimpleXLSXReader extends AbstractPoiReader{
    XSSFWorkbook xssfWorkbook;
    public SimpleXLSXReader(String filePath){
        super(filePath);
    }

    @Override
    public void initWorkbook() {
        xssfWorkbook = XLSXExcelUtil.getXSSFWorkbook(path);
    }

    @Override
    public void startSheet(BSheet bSheet) {
        int sheetIndex = bSheet.sheetIndex();
        int startRow = bSheet.startRow();
        int startCol = bSheet.startColumn();


    }
}
