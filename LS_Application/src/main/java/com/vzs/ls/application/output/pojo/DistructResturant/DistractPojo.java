package com.vzs.ls.application.output.pojo.DistructResturant;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

/**
 * Created by byao on 1/4/15.
 */
public class DistractPojo {
    public static class ReachRate{
        @Getter
        double reached = 0L;
        @Getter
        double materNoCount=0L;
        public ReachRate(){

        }
        public void add(ReachRate reachRateAdd){
            this.reached = this.reached + reachRateAdd.getReached();
            this.materNoCount = this.materNoCount + reachRateAdd.getMaterNoCount();
        }
        public void add(boolean isReached){
            if(isReached){
                reached++;
            }
            materNoCount++;
        }
        public Double getReate(){
            if(materNoCount == 0){
                return null;
            }
            return reached/materNoCount;
        }
    }
    @Getter
    Map<String,DistractPojoRow> materialNoMaps = Maps.newHashMap();

    @Getter
    Map<String,ReachRate> reachedRateMaps = Maps.newHashMap();

    ReachRate overAllReachRate;

    public ReachRate getOverAllReachRate(){
        overAllReachRate = new ReachRate();
        for (ReachRate reachRate : reachedRateMaps.values()) {
            overAllReachRate.add(reachRate);
        }
        ;

        return overAllReachRate;
    }

    //for national xls
    @Getter
    Map<String, Double> materialNoToSum = Maps.newHashMap();


    public void add(String materalNo,String name,String restuantName,Double diffSum, boolean isReach){

        //for rachRate
        ReachRate reachRate = reachedRateMaps.get(restuantName);
        if(reachRate == null){
            reachRate = new ReachRate();
            reachedRateMaps.put(restuantName,reachRate);
        }
        reachRate.add(isReach);


        DistractPojoRow distractPojoRow = materialNoMaps.get(materalNo);
        if(distractPojoRow == null){
            distractPojoRow = new DistractPojoRow();
            distractPojoRow.setMaterialNo(materalNo);
            distractPojoRow.setMaterName(name);
            materialNoMaps.put(materalNo,distractPojoRow);
        }

        Double sumDiff = materialNoToSum.get(materalNo);
        sumDiff = sumDiff == null ? 0D : sumDiff;
        diffSum = diffSum == null ?0D:diffSum;
        sumDiff = sumDiff + diffSum;
        materialNoToSum.put(materalNo,sumDiff);

        distractPojoRow.putDiff(restuantName,diffSum);

    }


}