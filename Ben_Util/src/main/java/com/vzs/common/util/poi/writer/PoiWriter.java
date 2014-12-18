package com.vzs.common.util.poi.writer;

import com.vzs.common.util.poi.pojo.BCell;
import com.vzs.common.util.poi.pojo.BRow;
import com.vzs.common.util.poi.pojo.BSheet;
import lombok.AllArgsConstructor;
import utils.BReflectHelper;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by byao on 12/15/14.
 */
@AllArgsConstructor
public abstract class PoiWriter {
    String filePath;
    String templatePath;
    int currentRowIndex =0;

    public PoiWriter(String filePath, String templatePath) {
        this.filePath = filePath;
        this.templatePath = templatePath;
    }

    public abstract void prepareWorkbook();

    public abstract void prepareSheet(BSheet bSheet);

    protected void writeSheet(Object sheetInstance) {
        for (Field field : sheetInstance.getClass().getDeclaredFields()) {
            BRow brow = field.getAnnotation(BRow.class);
            if(brow != null){
                //only support one row list in sheet
                List row = (List)BReflectHelper.getValue(sheetInstance,field);
                writeRows(row);
                break;
            }
        }

    }

    protected void writeRows(List rowInstanceList){
        
        for(Object rowInstance : rowInstanceList){
            prepareCurrentRow();
            writeSingleRow(rowInstance);
            currentRowIndex++ ;
        }
    }

    protected abstract void prepareCurrentRow();

    protected void writeSingleRow(Object rowInstance){
        for (Field field : rowInstance.getClass().getDeclaredFields()) {
            BCell bCell = field.getAnnotation(BCell.class);
            if(bCell != null){
                Object cellInstance = BReflectHelper.getValue(rowInstance,field);
                writeCell(cellInstance,field);
            }
        }
        
    }
    protected abstract void writeCell(Object cellInstance, Field field);

    public abstract void writeWorkbook();
}
