package com.vzs.ls.application.ui;

import com.google.common.collect.Multimap;
import com.vzs.common.util.log.SingleThreadLogUtil;
import com.vzs.ls.application.distractExecutor.DistractExecutorImpl;
import com.vzs.ls.application.input.pojo.InputContext;
import com.vzs.ls.application.logicExecutor.SingleRestaurantExecutorImpl;
import com.vzs.ls.application.nationExecutor.NationExecutorImpl;
import com.vzs.ls.application.output.pojo.DistructResturant.DistractPojo;
import com.vzs.ls.application.output.pojo.SingleRestaruant.SingleRestaurantWookbook;

import java.util.Map;

/**
 * Created by ben.yao on 12/6/2014.
 */
public class CalaulateMain {
    public static void start(InputContext inputContext){
        SingleRestaurantExecutorImpl executor = new SingleRestaurantExecutorImpl(inputContext);
        executor.execute();

        Multimap<String, SingleRestaurantWookbook> dmToRestuarnts = executor.getDmToRestuarnts();
        DistractExecutorImpl distractExecutor = new DistractExecutorImpl(inputContext);
        distractExecutor.setDmToRestuarnts(dmToRestuarnts);
        distractExecutor.execute();

        Map<String, DistractPojo> dmToDP = distractExecutor.getDmToDP();
        NationExecutorImpl nationExecutor = new NationExecutorImpl(inputContext,dmToDP,distractExecutor.getUniqueMaterialNo());
        nationExecutor.execute();

        SingleThreadLogUtil.getInstance().flush(inputContext.getLogFolder());
    }

	public static void main(String... args){
        start(new InputContext("/users/byao/Ben/personal/YUM/COS追踪工具制作/"));
	}
}
