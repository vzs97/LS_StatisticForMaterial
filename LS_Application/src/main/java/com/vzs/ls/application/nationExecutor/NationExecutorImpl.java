package com.vzs.ls.application.nationExecutor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vzs.common.util.dao.BWorkbookDaoImpl;
import com.vzs.ls.application.input.pojo.InputContext;
import com.vzs.ls.application.output.pojo.DistructResturant.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by byao on 1/10/15.
 */
public class NationExecutorImpl {
    BWorkbookDaoImpl inputDao = new BWorkbookDaoImpl();
    InputContext inputContext;

    Map<String,DistractPojo> dmToDP;
    Set<String> dms;
    Map<String, String> uniqueMaterialNos;
    public NationExecutorImpl(InputContext inputContext,Map<String,DistractPojo> dmToDP,Map<String, String> uniqueMaterialNos){
        this.inputContext = inputContext;
        this.dmToDP = dmToDP;
        dms = dmToDP.keySet();
        this.uniqueMaterialNos = uniqueMaterialNos;

    }

    private DistractTitleRow getDistractTitleRow(){
        DistractTitleRow titleRow = new DistractTitleRow();
        List<String> titles = Lists.newArrayList();
        titles.add("产品组ID");
        titles.add("物料名称");
        titles.add("区域小计");
        titles.addAll(dms);

        titleRow.setTitles(titles);
        return titleRow;
    }

    public void execute(){
        DistractWorkbook distractWorkbook = new DistractWorkbook();
        DistractSheet distractSheet = new DistractSheet();
        distractWorkbook.setDistractSheet(distractSheet);

        distractSheet.setDistractTitleRow(getDistractTitleRow());

        List<DistractRow> distractRowList = Lists.newArrayList();
        distractSheet.setDistractRowList(distractRowList);

        Double overAllSum = 0D;
        Map<String, Double> dmCount = Maps.newHashMap();

        for (Map.Entry<String, String> materNoToMaterName : uniqueMaterialNos.entrySet()) {
            DistractRow row = new DistractRow();
            distractRowList.add(row);


            String materialNo = materNoToMaterName.getKey();
            String materialName = materNoToMaterName.getValue();
            row.setMaterialNo(materialNo);
            row.setName(materialName);
            List<Double> resValues = Lists.newArrayList();
            Double distSum = 0D;//for row sub sum
            for(String dm : dms){
                DistractPojo distractPojo = dmToDP.get(dm);
                Double sumDiff = distractPojo.getMaterialNoToSum().get(materialNo);
                if(sumDiff == null){
                    sumDiff = 0D;
                }
                resValues.add(sumDiff);

                distSum = distSum + sumDiff;

                Double aDouble = dmCount.get(dm);
                if(aDouble == null){
                    aDouble = 0D;
                }
                aDouble = aDouble + sumDiff;
                dmCount.put(dm,aDouble);
            }
            row.setDistractCount(distSum);
            row.setResValues(resValues);


            overAllSum = overAllSum + distSum;

        }

        //sub sum
        DistractRow subSum = new DistractRow();
        subSum.setName("差异金额小计（元");
        subSum.setDistractCount(overAllSum);
        List<Double> subCountList = Lists.newArrayList();
        subSum.setResValues(subCountList);
        for(String dm : dms){
            Double dmSum = dmCount.get(dm);
            subCountList.add(dmSum);
        }

        distractRowList.add(subSum);

        //rate
        DistractRowPercent rateRow = new DistractRowPercent();
        rateRow.setName("得率达成%:");
        List<Double> rateRowList = Lists.newArrayList();
        rateRow.setResValues(rateRowList);

        DistractPojo.ReachRate nationReachRate = new DistractPojo.ReachRate();
        for(String dm : dms){
            DistractPojo distractPojo = dmToDP.get(dm);
            DistractPojo.ReachRate overAllReachRate = distractPojo.getOverAllReachRate();
            rateRowList.add(overAllReachRate.getReate());

            nationReachRate.add(overAllReachRate);
        }
        rateRow.setDistractCount(nationReachRate.getReate());
        distractSheet.setDistractRowPercent(rateRow);

        inputDao.writeWorkbook(inputContext.getDmFolder(),"全国.xls",null,distractWorkbook);
    }


}
