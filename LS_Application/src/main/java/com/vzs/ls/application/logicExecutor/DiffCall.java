package com.vzs.ls.application.logicExecutor;

import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantRow;

/**
 * Created by byao on 12/14/14.
 */
public class DiffCall extends SingleRestaurantRowCall {
    @Override
    public void call(SingleRestaurantRow singleRestaurantRow) {
        if(singleRestaurantRow.getTheoryCousumption() != null && singleRestaurantRow.getActualyCoumption() != null) {
            singleRestaurantRow.setDiff(singleRestaurantRow.getTheoryCousumption() - singleRestaurantRow.getActualyCoumption());
        }
    }
}
