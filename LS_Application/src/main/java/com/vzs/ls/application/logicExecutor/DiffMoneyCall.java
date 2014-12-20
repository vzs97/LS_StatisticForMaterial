package com.vzs.ls.application.logicExecutor;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
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
 * Created by byao on 12/14/14.
 */
@Data
@AllArgsConstructor
public class DiffMoneyCall extends SingleRestaurantRowCall {
    SingleRestaurantExecutorImpl singleRestaurantExecutorImpl;
    ResturantMaintainRow resturantMaintainRow ;


    @Override
    public void call(SingleRestaurantRow singleRestaurantRow) {

        Map<String, WeeklyInventoryWorkbook> resturanNoToWorkbook = singleRestaurantExecutorImpl.getResturanNoToWorkbook();
        WeeklyInventoryWorkbook weeklyInventoryWorkbook = resturanNoToWorkbook.get(resturantMaintainRow.getResturantNo());
        if(weeklyInventoryWorkbook == null){
            System.out.println("Can't find PIIT for restaurant " + resturantMaintainRow.getResturantNo());
            return;
        }
        Map<String, WeeklyInventoryRow> materialNoToRow = weeklyInventoryWorkbook.getWeeklyInventorySheet().getMaterialNoToRow();


        WeeklyInventoryRow weeklyInventoryRow = singleRestaurantExecutorImpl.getIfHave(singleRestaurantRow.getMaterialNo(),materialNoToRow);


        if(weeklyInventoryRow == null){
            System.out.println("Can't find PIIT for restaurant  material No " + singleRestaurantRow.getMaterialNo());
            return;
        }

        Double referencePrice=weeklyInventoryRow.getUnitPrice();
        if(singleRestaurantRow.getDiff() != null && referencePrice != null) {
            singleRestaurantRow.setDiffCount(singleRestaurantRow.getDiff() * referencePrice);
        }
    }
}
