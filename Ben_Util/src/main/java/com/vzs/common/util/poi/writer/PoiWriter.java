package com.vzs.common.util.poi.writer;

import com.vzs.common.util.poi.pojo.BSheet;
import lombok.AllArgsConstructor;

import java.lang.reflect.Field;

/**
 * Created by byao on 12/15/14.
 */
@AllArgsConstructor
public abstract class PoiWriter {
    String filePath;
    String templatePath;

    public abstract void prepareWorkbook();

    public abstract void prepareSheet(BSheet bSheet);

    public void writeSheet(Field field) {
        //TODO:
    }
}
