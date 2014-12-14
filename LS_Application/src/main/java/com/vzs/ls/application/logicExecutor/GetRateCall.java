package com.vzs.ls.application.logicExecutor;

import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantRow;
import lombok.Data;

/**
 * Created by byao on 12/14/14.
 */
@Data
public class GetRateCall extends SingleRestaurantRowCall {
    @Override
    public void call(SingleRestaurantRow singleRestaurantRow) {
        if(singleRestaurantRow.getTheoryCousumption() != null && singleRestaurantRow.getActualyCoumption() != null) {
            singleRestaurantRow.setRate(singleRestaurantRow.getTheoryCousumption() / singleRestaurantRow.getActualyCoumption());
        }
    }
}
