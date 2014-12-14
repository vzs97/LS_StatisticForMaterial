package com.vzs.ls.application.logicExecutor;

import com.vzs.ls.application.input.pojo.ResturantMaintain.ResturantMaintainRow;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantRow;
import lombok.AllArgsConstructor;
import lombok.Data;

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
        //TODO: need read xls for each resturant
        singleRestaurantRow.setActualyCoumption(123D);
    }
}
