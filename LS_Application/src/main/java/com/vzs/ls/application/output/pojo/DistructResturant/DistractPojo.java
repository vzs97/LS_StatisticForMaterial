package com.vzs.ls.application.output.pojo.DistructResturant;

import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Map;

/**
 * Created by byao on 1/4/15.
 */
public class DistractPojo {
    @Getter
    Map<String,DistractPojoRow> materialNoMaps = Maps.newHashMap();
    long reached = 0L;
    long materNoCount=0L;
    public void add(String materalNo,String name,String restuantName,Double diffSum){
        DistractPojoRow distractPojoRow = materialNoMaps.get(materalNo);
        if(distractPojoRow == null){
            distractPojoRow = new DistractPojoRow();
            distractPojoRow.setMaterialNo(materalNo);
            distractPojoRow.setMaterName(name);
            materialNoMaps.put(materalNo,distractPojoRow);
        }
        distractPojoRow.putDiff(restuantName,diffSum);

    }


}