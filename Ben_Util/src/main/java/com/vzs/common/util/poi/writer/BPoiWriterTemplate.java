package com.vzs.common.util.poi.writer;

import com.vzs.common.util.poi.pojo.BSheet;
import com.vzs.common.util.poi.pojo.BWorkbook;
import lombok.Getter;
import lombok.Setter;
import utils.BAnnotationHelper;
import utils.BReflectHelper;

import java.lang.reflect.Field;

/**
 * Created by byao on 12/15/14.
 */
public class BPoiWriterTemplate<T> {
    String filePath;
    @Getter
    private T bWorkbook;
    @Setter
    String templatePath;
    private PoiWriter poiWriter;

    public BPoiWriterTemplate(String filePath, Class clazz){
        this.filePath = filePath;
        this.bWorkbook = BReflectHelper.newInstance(clazz);
        BAnnotationHelper.isAnnotationPresent(bWorkbook.getClass(), BWorkbook.class, "Haven't pass a BWorkbook annotation class");
    }
    public void execute(){
        if(filePath.endsWith(".xlsx")){

        }else if(filePath.endsWith(".xls")){
            poiWriter = new SimpleXlsWriter(filePath,templatePath);
        }
        poiWriter.prepareWorkbook();
        for (Field field : bWorkbook.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(BSheet.class)){
                write(field);
            }
        }
    }

    private void write(Field field) {
        BSheet bSheet = field.getAnnotation(BSheet.class);
        poiWriter.prepareSheet(bSheet);
        poiWriter.writeSheet(field);

    }
}
