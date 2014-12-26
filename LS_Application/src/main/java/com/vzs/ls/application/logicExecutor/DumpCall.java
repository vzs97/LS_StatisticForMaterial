package com.vzs.ls.application.logicExecutor;

import com.vzs.common.util.log.SingleThreadLogUtil;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantRow;

/**
 * Created by byao on 12/20/14.
 */
public class DumpCall extends SingleRestaurantRowCall {
    @Override
    public void call(SingleRestaurantRow singleRestaurantRow) {
        String materialNo = singleRestaurantRow.getMaterialNo();
        if("8691002Q".equals(singleRestaurantRow.getMaterialNo())){
            SingleThreadLogUtil.log(null);
        }
    }
}
