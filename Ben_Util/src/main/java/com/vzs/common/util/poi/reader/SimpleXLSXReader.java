package com.vzs.common.util.poi.reader;

import com.vzs.common.util.poi.pojo.BSheet;
import lombok.Data;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by vzs on 2014/12/3.
 */
public class SimpleXLSXReader extends AbstractPoiReader{
    XSSFWorkbook xssfWorkbook;
    public SimpleXLSXReader(String filePath){
        super(filePath);
    }

    @Override
    public void initWorkbook() throws IOException {
        xssfWorkbook = new XSSFWorkbook(new FileInputStream(filePath));
    }

    @Override
    public void startSheet(BSheet bSheet) {
        int sheetIndex = bSheet.sheetIndex();
        int startRow = bSheet.startRow();
        int startCol = bSheet.startColumn();


    }
}
