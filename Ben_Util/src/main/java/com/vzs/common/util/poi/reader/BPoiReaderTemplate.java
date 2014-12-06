package com.vzs.common.util.poi.reader;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Getter;
import utils.BAnnotationHelper;
import utils.BReflectHelper;

import java.lang.reflect.Field;

/**
 * Created by vzs on 2014/12/2.
 */
public class BPoiReaderTemplate<T> {
    String filePath;
    @Getter
    private T bWorkbook;
    PoiReader poiReader;
    public BPoiReaderTemplate(String filePath, Class clazz){
        this.filePath = filePath;
        this.bWorkbook = BReflectHelper.newInstance(clazz);
        BAnnotationHelper.isAnnotationPresent(bWorkbook.getClass(),BWorkbook.class,"Haven't pass a BWorkbook annotation class");
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
        BSheet bSheet = field.getAnnotation(BSheet.class);
        poiReader.prepareSheet(bSheet);
        Object sheet = poiReader.readSheet(field.getType());
        BReflectHelper.setValues(bWorkbook, field, sheet);
    }


}
