package com.vzs.ls.application.logicExecutor;

import com.vzs.common.util.log.SingleThreadLogUtil;
import com.vzs.ls.application.input.pojo.DishesSellerStatistic.DishesSellerStatisticRow;
import com.vzs.ls.application.input.pojo.DishesSellerStatistic.DishesSellerStatisticWorkbook;
import com.vzs.ls.application.input.pojo.InventoryRecipeTransfer.InventoryRecipeTransferRow;
import com.vzs.ls.application.input.pojo.LSRecipe.*;
import com.vzs.ls.application.input.pojo.ResturantMaintain.ResturantMaintainRow;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantRow;
import lombok.AllArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 理论用量
 * Created by byao on 12/13/14.
 */
@AllArgsConstructor
public class TheoryCousumptionCall extends SingleRestaurantRowCall {
    SingleRestaurantExecutorImpl singleRestaurantExecutorImpl;
    ResturantMaintainRow resturantMaintainRow ;
    @Override
    public void call(SingleRestaurantRow singleRestaurantRow) {
        if(singleRestaurantRow.getMaterialNo().equals("86910649")){
            System.out.println();
        }
        Map<String,InventoryRecipeTransferRow> idToRow=singleRestaurantExecutorImpl.getInventoryRecipeTransferWorkbook().getIdToRow();
        String materialNo=singleRestaurantRow.getMaterialNo();

        DishesSellerStatisticWorkbook dishesSellerStatisticWorkbook = singleRestaurantExecutorImpl.getResturantNameToWorkbook().get(resturantMaintainRow.getResturantName());
        Map<String, DishesSellerStatisticRow> dishesSellerStatisticRowMap = dishesSellerStatisticWorkbook.getDishesSellerStatisticSheet().getDishesSellerStatisticRowMap();

        if(materialNo.matches("^191.*$")){
           //pot kind
            String portVersion = resturantMaintainRow.getPotVersion();
            LSRecipeWorkbook lsRecipeWorkbook= singleRestaurantExecutorImpl.getLsRecipeWorkbook();
            //get pot recipe
            LSRecipePotSheet lsRecipePotSheet = lsRecipeWorkbook.getLSRecipePotSheet(portVersion);
            //get products by materid
            Collection<LSRecipePotRow> lsRecipePotRowList = lsRecipePotSheet.getMultimap().get(materialNo);

            Double amount=0D;
            for(LSRecipePotRow lsRecipePotRow:lsRecipePotRowList){
                Double unit = lsRecipePotRow.getRecipeCount();
                String sumOfGodSystemID = lsRecipePotRow.getSunOfGodSystemId();
                DishesSellerStatisticRow dishesSellerStatisticRow = dishesSellerStatisticRowMap.get(sumOfGodSystemID);
                Double saled = dishesSellerStatisticRow.getSales();
                amount += unit * saled;
            }
            InventoryRecipeTransferRow inventoryRecipeTransferRow = idToRow.get(singleRestaurantRow.getTempJDECode());
            Double transferUnit = inventoryRecipeTransferRow.getTransferUnit();
            Double theoryUnit=amount/transferUnit;
            singleRestaurantRow.setTheoryCousumption(theoryUnit);
        }else{
            LSRecipeExceptPotSheet exceptPotRecipe = singleRestaurantExecutorImpl.getLsRecipeWorkbook().getExceptPotRecipe();
            Collection<LSRecipeExceptPotRow> lsRecipeExceptPotRows = exceptPotRecipe.getMultimap().get(materialNo);
            Double amount=0D;
            if(CollectionUtils.isEmpty(lsRecipeExceptPotRows)){
                SingleThreadLogUtil.log("Can't find " + materialNo + " from 配方表");
            }
            for (LSRecipeExceptPotRow lsRecipeExceptPotRow : lsRecipeExceptPotRows) {
                Double recipeCount = lsRecipeExceptPotRow.getRecipeCount();
                String sunOfGodSystemId = lsRecipeExceptPotRow.getSunOfGodSystemId();
                DishesSellerStatisticRow dishesSellerStatisticRow = dishesSellerStatisticRowMap.get(sunOfGodSystemId);
                if(dishesSellerStatisticRow == null){
                    SingleThreadLogUtil.log("Can't find 天子星代码" + sunOfGodSystemId);
                    continue;
                }
                if(recipeCount == null){
                    SingleThreadLogUtil.log("can't find 配方克数 for 天子星" + sunOfGodSystemId);
                    continue;
                }
                Double sales = dishesSellerStatisticRow.getSales();
                amount += recipeCount * sales;
            }
//            System.out.println(materialNo);
            InventoryRecipeTransferRow inventoryRecipeTransferRow = idToRow.get(singleRestaurantRow.getTempJDECode());
            if(inventoryRecipeTransferRow == null){
                return;
            }
            Double transferUnit = inventoryRecipeTransferRow.getTransferUnit();
            Double theoryUnit = amount/transferUnit;
            singleRestaurantRow.setTheoryCousumption(theoryUnit);

        }
        //deonmistor  1:find jde code in 产品组ID.xlsx by materila NO,
        // 2:then find which one exist in PIIT, V columns not null, if more than one , get the value bigger than 0.
        // 3:then use this jde id to get the transfer in 盘点与配方单位转换表.xlsx
        //4: if can't find in step2, more than one meet the requirement , insert a a comments

    }
}
