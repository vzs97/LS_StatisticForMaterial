package com.vzs.common.util.poi.pojo;

import com.vzs.common.util.poi.reader.BPoiReaderTemplate;

/**
 * Created by vzs on 2014/12/2.
 */
public class PoiTestMain {
    public static void main(String... args){
        String xlsPath="D:\\LS_MaterialStatistic\\COS追踪工具制作\\COS追踪工具制作\\物料维护表.xlsx";
        BPoiReaderTemplate<LSMaterialWorkbook> bPoiReaderTemplate = new BPoiReaderTemplate<LSMaterialWorkbook>(xlsPath,LSMaterialWorkbook.class);
        bPoiReaderTemplate.execute();


    }
}
