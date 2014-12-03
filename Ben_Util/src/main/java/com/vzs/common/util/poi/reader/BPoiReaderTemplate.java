package com.vzs.common.util.poi.reader;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import utils.BReflectHelper;

import java.lang.reflect.Field;

/**
 * Created by vzs on 2014/12/2.
 */
public class BPoiReaderTemplate<T> {
    String filePath;
    T bWorkbook;
    PoiReader poiReader;
    public BPoiReaderTemplate(String filePath, Class clazz){
        this.filePath = filePath;
        this.bWorkbook = BReflectHelper.newInstance(clazz);
        if(!bWorkbook.getClass().isAnnotationPresent(BWorkbook.class)){
            throw new IllegalArgumentException("Haven't pass a BWorkbook annotation class");
        }
    }
    public void execute(){
        if(filePath.endsWith(".xlsx")){
            poiReader = new SimpleXLSXReader(filePath);
            for (Field field : bWorkbook.getClass().getDeclaredFields()) {
                if(field.isAnnotationPresent(BSheet.class)){
                    readSheet(field);
                }
            }
        }
    }

    private void readSheet(Field field){
        Object sheet = BReflectHelper.newInstance(field.getClass());
        BSheet bSheet = sheet.getClass().getAnnotation(BSheet.class);



    }

}
