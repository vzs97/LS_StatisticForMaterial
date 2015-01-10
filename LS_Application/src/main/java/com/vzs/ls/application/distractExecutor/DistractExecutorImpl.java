package com.vzs.ls.application.distractExecutor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.vzs.common.util.dao.BWorkbookDaoImpl;
import com.vzs.common.util.poi.pojo.BColors;
import com.vzs.ls.application.input.pojo.InputContext;
import com.vzs.ls.application.output.pojo.DistructResturant.*;
import com.vzs.ls.application.output.pojo.SingleRestaruant.RateStyle;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantRow;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantWookbook;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by byao on 1/4/15.
 */

public class DistractExecutorImpl {
    @Setter
    Multimap<String,SingleRestaurantWookbook> dmToRestuarnts;

    BWorkbookDaoImpl inputDao = new BWorkbookDaoImpl();
    InputContext inputContext;

    DistractPojo distractPojo;
    Set<String> resturantNames;


    @Getter
    Map<String,String> uniqueMaterialNo = Maps.newHashMap();

    @Getter
    Map<String,DistractPojo> dmToDP = Maps.newHashMap();

    public DistractExecutorImpl(InputContext inputContext){
        this.inputContext = inputContext;
    }

    public void execute() {

        Set<String> dms = dmToRestuarnts.keySet();
        for(String dm : dms){
            distractPojo = new DistractPojo();
            resturantNames = Sets.newLinkedHashSet();
            Collection<SingleRestaurantWookbook> singleRestaurantWookbooks = dmToRestuarnts.get(dm);
            for (SingleRestaurantWookbook singleRestaurantWookbook : singleRestaurantWookbooks) {
                String resturantName = singleRestaurantWookbook.getResturantName();
                resturantNames.add(resturantName);

                RateStyle rateStyle = new RateStyle();
                for (SingleRestaurantRow singleRestaurantRow : singleRestaurantWookbook.getSingleRestaurantSheet().getSingleRestaurantRowList()) {
                    BColors color = rateStyle.getColor(singleRestaurantRow.getRate(), singleRestaurantRow.getTargetValue());
                    boolean isReach=true;
                    if(color == null || BColors.RED.equals(color)){
                        isReach=false;
                    }
                    uniqueMaterialNo.put(singleRestaurantRow.getMaterialNo(),singleRestaurantRow.getName());

                    distractPojo.add(singleRestaurantRow.getMaterialNo(),singleRestaurantRow.getName(),resturantName,singleRestaurantRow.getDiffCount(),isReach);
                }


            }
            dmToDP.put(dm,distractPojo);
            convertToWorkbookByDm(dm);
        }
    }

    private DistractTitleRow getDistractTitleRow(){
        DistractTitleRow titleRow = new DistractTitleRow();
        List<String> titles = Lists.newArrayList();
        titles.add("产品组ID");
        titles.add("物料名称");
        titles.add("区域小计");
        titles.addAll(resturantNames);

        titleRow.setTitles(titles);
        return titleRow;
    }

    private DistractWorkbook convertToWorkbookByDm(String dm){
        DistractWorkbook workbook = new DistractWorkbook();
        DistractSheet distractSheet = new DistractSheet();
        workbook.setDistractSheet(distractSheet);

        distractSheet.setDistractTitleRow(getDistractTitleRow());

        List<DistractRow> distractRows = Lists.newArrayList();
        distractSheet.setDistractRowList(distractRows);

        Map<String, Double> resNoCount = Maps.newHashMap();
        Double subDistractCount = 0D;

        Map<String, DistractPojoRow> materialNoMaps = distractPojo.getMaterialNoMaps();
        for (Map.Entry<String, DistractPojoRow> stringDistractPojoRowEntry : materialNoMaps.entrySet()) {
            String materialNo = stringDistractPojoRowEntry.getKey();
            DistractPojoRow distractPojoRow = stringDistractPojoRowEntry.getValue();

            DistractRow row = new DistractRow();
            row.setMaterialNo(materialNo);
            row.setName(distractPojoRow.getMaterName());

            Map<String, DistractResturantPojo> distractResturantPojoMap = distractPojoRow.getDistractResturantPojoMap();
            List<Double> resValues = Lists.newArrayList();
            Double distractCount = 0D;
            for(String restNo : resturantNames){
                DistractResturantPojo distractResturantPojo = distractResturantPojoMap.get(restNo);
                if( distractResturantPojo == null) {
                    resValues.add(0D);
                } else {
                    Double diff = distractResturantPojo.getDiff();
                    if(diff != null) {
                        distractCount = distractCount + diff;
                    }else {
                        diff = 0D;
                    }
                    resValues.add(diff);

                    //for subCount
                    Double subCount = resNoCount.get(restNo);
                    if(subCount == null){
                        subCount = 0D;
                    }
                    subCount = subCount + diff;
                    resNoCount.put(restNo,subCount);

                }
            }

            row.setDistractCount(distractCount);
            row.setResValues(resValues);

            distractRows.add(row);

            subDistractCount = subDistractCount + distractCount;
        }
        ;

        //for 小计
        DistractRow row = new DistractRow();
        row.setName("差异金额小计（元");
        row.setDistractCount(subDistractCount);
        List<Double> subCountList = Lists.newArrayList();
        for(String restNo : resturantNames){
            Double aDouble = resNoCount.get(restNo);
            subCountList.add(aDouble);
        }
        row.setResValues(subCountList);
        distractRows.add(row);

        //达标率
        DistractRowPercent rateRow = new DistractRowPercent();
        rateRow.setName("得率达成%:");
        List<Double> rateRowList = Lists.newArrayList();
        Map<String, DistractPojo.ReachRate> reachedRateMaps = distractPojo.getReachedRateMaps();
        DistractPojo.ReachRate overAll = new DistractPojo.ReachRate();
        for(String restNo : resturantNames){
            DistractPojo.ReachRate reachRate = reachedRateMaps.get(restNo);
            rateRowList.add(reachRate.getReate());
            overAll.add(reachRate);
        }
        rateRow.setResValues(rateRowList);
        rateRow.setDistractCount(overAll.getReate());
        distractSheet.setDistractRowPercent(rateRow);


        inputDao.writeWorkbook(inputContext.getDmFolder(),dm+".xls",null,workbook);

        return workbook;
    }
}
