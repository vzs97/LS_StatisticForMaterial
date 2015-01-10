package com.vzs.ls.application.output.pojo.DistructResturant;

import com.google.common.collect.Maps;
import com.vzs.common.util.log.SingleThreadLogUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * Created by byao on 1/4/15.
 */
@Data
public class DistractPojoRow {
    String materialNo;
    String materName;
    Double distractSum;
    boolean isReach;
    @Getter
    Map<String,DistractResturantPojo> distractResturantPojoMap = Maps.newHashMap();
    public void putDiff(String resturantName, Double diffSum){
        DistractResturantPojo distractResturantPojo = distractResturantPojoMap.get(resturantName);
        if(distractResturantPojo == null){
            distractResturantPojo = new DistractResturantPojo();
            distractResturantPojo.setResturantName(resturantName);
            distractResturantPojo.setDiff(diffSum);

            distractResturantPojoMap.put(resturantName,distractResturantPojo);
        }else{
            SingleThreadLogUtil.log("for material ["+materialNo+"], restaurant ["+resturantName+"] already have the 差异值, ignore the non-first one");
        }
    }
}
