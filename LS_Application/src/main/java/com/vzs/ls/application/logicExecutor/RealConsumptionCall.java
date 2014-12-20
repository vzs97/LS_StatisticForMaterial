package com.vzs.ls.application.logicExecutor;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.vzs.ls.application.input.pojo.LSRecipe.LSRecipeWorkbook;
import com.vzs.ls.application.input.pojo.ProductIDReference.ProductIDReferenceRow;
import com.vzs.ls.application.input.pojo.ProductIDReference.ProductIDReferenceWorkbook;
import com.vzs.ls.application.input.pojo.ResturantMaintain.ResturantMaintainRow;
import com.vzs.ls.application.input.pojo.WeeklyInventory.WeeklyInventoryRow;
import com.vzs.ls.application.input.pojo.WeeklyInventory.WeeklyInventoryWorkbook;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantRow;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 实际用量
 * Created by byao on 12/14/14.
 */
@Data
@AllArgsConstructor
public class RealConsumptionCall extends SingleRestaurantRowCall{
    SingleRestaurantExecutorImpl singleRestaurantExecutorImpl;
    ResturantMaintainRow resturantMaintainRow;



    @Override
    public void call(SingleRestaurantRow singleRestaurantRow) {
        Map<String, WeeklyInventoryWorkbook> resturanNoToWorkbook = singleRestaurantExecutorImpl.getResturanNoToWorkbook();
        WeeklyInventoryWorkbook weeklyInventoryWorkbook = resturanNoToWorkbook.get(resturantMaintainRow.getResturantNo());
        if(weeklyInventoryWorkbook == null){
            System.out.println("Can't find PIIT for restaurant" + resturantMaintainRow.getResturantNo());
            return;
        }

        WeeklyInventoryRow weeklyInventoryRow  = singleRestaurantExecutorImpl.getIfHave(singleRestaurantRow.getMaterialNo(),weeklyInventoryWorkbook.getWeeklyInventorySheet().getMaterialNoToRow());

        if(weeklyInventoryRow == null){
            System.out.println("Can't find PIIT for restaurant  material No " + singleRestaurantRow.getMaterialNo());
            return;
        }

        singleRestaurantRow.setActualyCoumption(weeklyInventoryRow.getMonthlySale());
    }
}
