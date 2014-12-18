package com.vzs.common.util.poi.writer;

import com.google.common.collect.Maps;
import com.vzs.common.util.poi.pojo.*;
import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.util.StringUtils;
import utils.BReflectHelper;
import utils.BWorkbookUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;

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

    protected HSSFCellStyle getStyle(Object rowInstance,Field field){

        BCell bCell = field.getAnnotation(BCell.class);
        BCell.TYPES types = bCell.types();
        BColors bColor = null;

        BStyle bStyle = field.getAnnotation(BStyle.class);
        if(bStyle != null){
            Object sytleHanld = BReflectHelper.newInstance(bStyle.styleHanlde());
            if(sytleHanld instanceof BStyleAbstract){
                BStyleAbstract bStyleAbstract = (BStyleAbstract)sytleHanld;
                if(bStyle.method().equals(BStyleMethod.COLOR)){
                    Object currentValue = BReflectHelper.getValue(rowInstance,field);
                    String referenceObj = bStyle.refereceObj();
                    Object refereceValue=BReflectHelper.getValue(rowInstance,referenceObj);
                    bColor = bStyleAbstract.getColor(currentValue,refereceValue);

                }
            }
        }

        String styleKey=sytleKey(types,bColor);
        HSSFCellStyle style = styleCache.get(styleKey);
        if(style == null){
            style = hssfWorkbook.createCellStyle();
            if(BColors.RED.equals(bColor)) {
                style.setFillForegroundColor(HSSFColor.RED.index);
                style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            }
            if(BCell.TYPES.PERCENT.equals(types)){
                style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
            }

            styleCache.put(styleKey,style);
        }

        return style;
    }

    Map<String,HSSFCellStyle> styleCache = Maps.newHashMap();

    private String sytleKey(Object... objs){
        StringBuilder sb = new StringBuilder();
        for(Object obj : objs){
            sb.append(obj).append("@");
        }
        return sb.toString();
    }

    @Override
    protected void writeCell(Object rowInstance, Field field) {
        Object cellInstance = BReflectHelper.getValue(rowInstance,field);

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

        HSSFCellStyle style = getStyle(rowInstance, field);
        if(style != null){
            cell.setCellStyle(style);
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
