package com.vzs.ls.application.logicExecutor;

import com.vzs.ls.application.input.pojo.ResturantMaintain.ResturantMaintainRow;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantRow;
import lombok.AllArgsConstructor;
import lombok.Data;

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

        //TODO:need 盘点表
        Double referencePrice=2D;
        if(singleRestaurantRow.getDiff() != null && referencePrice != null) {
            singleRestaurantRow.setDiffCount(singleRestaurantRow.getDiff() * referencePrice);
        }
    }
}
