package com.vzs.common.util.poi.writer;

import com.vzs.common.util.poi.pojo.BSheet;
import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by byao on 12/15/14.
 */
@AllArgsConstructor
public class SimpleXlsWriter extends PoiWriter{
    HSSFWorkbook hssfWorkbook;
    HSSFSheet currentSHeet;
    public SimpleXlsWriter(String filePath, String templatePath) {
        super(filePath,templatePath);
    }

    @Override
    public void prepareWorkbook() {
        if(!StringUtils.isEmpty(templatePath)){
            try {
                hssfWorkbook = new HSSFWorkbook(new FileInputStream(templatePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(hssfWorkbook == null){
            hssfWorkbook = new HSSFWorkbook();
        }
    }

    @Override
    public void prepareSheet(BSheet bSheet) {
        int sheetIndex = bSheet.sheetIndex();
        String sheetName = bSheet.sheetName();
        currentSHeet = null;
        if(sheetIndex >=0) {
            currentSHeet = hssfWorkbook.getSheetAt(sheetIndex);
        }else if(!StringUtils.isEmpty(sheetName)){
            currentSHeet = hssfWorkbook.getSheet(sheetName);
        }
        if(currentSHeet == null){
            currentSHeet = hssfWorkbook.createSheet(sheetName);
        }

    }
}
