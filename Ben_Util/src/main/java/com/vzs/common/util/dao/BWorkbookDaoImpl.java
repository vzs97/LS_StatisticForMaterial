package com.vzs.common.util.dao;

import com.vzs.common.util.poi.reader.BPoiReaderTemplate;
import com.vzs.common.util.poi.writer.BPoiWriterTemplate;
import lombok.NoArgsConstructor;
import utils.BWorkbookUtil;

/**
 * Created by byao on 12/26/14.
 */
@NoArgsConstructor
public class BWorkbookDaoImpl {
    public <T> T getWorkbook(String filepath,Class<T> clazz){
        BPoiReaderTemplate<T> bPoiReaderTemplate = new BPoiReaderTemplate<T>(filepath,clazz);
        bPoiReaderTemplate.execute();
        return bPoiReaderTemplate.getBWorkbook();
    }
    public <T> void writeWorkbook(String filePath,String fileName,String templatePath,T workkbook){
        BWorkbookUtil.mkdirsIfNotExist(filePath);
        BPoiWriterTemplate<T> bPoiWriterTemplate = new BPoiWriterTemplate(filePath+fileName,workkbook);
        bPoiWriterTemplate.setTemplatePath(templatePath);
        bPoiWriterTemplate.execute();;
    }
}
