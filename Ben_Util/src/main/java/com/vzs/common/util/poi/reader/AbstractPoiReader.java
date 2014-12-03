package com.vzs.common.util.poi.reader;

import com.vzs.common.util.poi.pojo.BSheet;
import lombok.Data;

/**
 * Created by vzs on 2014/12/3.
 */
public abstract class AbstractPoiReader implements PoiReader{
    String filePath;
    public AbstractPoiReader(String filePath){
        this.filePath=filePath;
        initWorkbook();
    }
    public abstract void initWorkbook();
    public abstract void startSheet(BSheet bSheet);
}
