package com.vzs.ls.application.output.pojo.SingleRestaruant;

import com.vzs.common.util.poi.pojo.BColors;
import com.vzs.common.util.poi.pojo.BStyleAbstract;

/**
 * Created by byao on 12/18/14.
 */
public class RateStyle extends BStyleAbstract {
    public BColors getColor(Object... obj){
        Double rate = (Double)obj[0];
        Double tartget = (Double)obj[1];
        if(rate == null || tartget == null){
            return null;
        }
        return (rate < tartget) || rate > 1 ? BColors.RED : BColors.NONE;
    }
}
