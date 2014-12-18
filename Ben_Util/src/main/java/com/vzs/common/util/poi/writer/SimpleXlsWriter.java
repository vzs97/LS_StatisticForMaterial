package com.vzs.common.util.poi.writer;

import com.vzs.common.util.poi.pojo.BCell;
import com.vzs.common.util.poi.pojo.BSheet;
import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.util.StringUtils;
import utils.BWorkbookUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by byao on 12/15/14.
 */

public class SimpleXlsWriter extends PoiWriter{
    HSSFWorkbook hssfWorkbook;
    HSSFSheet currentSHeet;
    HSSFRow currentRow;
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
        currentRowIndex = bSheet.startRow();
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

    @Override
    protected void prepareCurrentRow() {
        currentRow = currentSHeet.getRow(currentRowIndex);
        if(currentRow == null){
            currentRow = currentSHeet.createRow(currentRowIndex);
        }

    }

    protected HSSFCellStyle getStyle(Field field){
        HSSFCellStyle style = null;

        return style;
    }

    @Override
    protected void writeCell(Object cellInstance, Field field) {
        BCell bCell = field.getAnnotation(BCell.class);
        int columnIndex = BWorkbookUtil.ToIndex(bCell.column());
        HSSFCell cell = currentRow.getCell(columnIndex);
        if(cell == null){
            cell = currentRow.createCell(columnIndex);
        }
        if(cellInstance instanceof String) {
            cell.setCellValue((String)cellInstance);
        }else if(cellInstance instanceof Date){
            cell.setCellValue((Date)cellInstance);
        }else if(cellInstance instanceof Number){
            cell.setCellValue((Double)cellInstance);
        }else if(cellInstance instanceof Boolean){
            cell.setCellValue((Boolean)cellInstance);
        }

    }

    @Override
    public void writeWorkbook() {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(filePath);
            hssfWorkbook.write(fileOut);
            fileOut.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
