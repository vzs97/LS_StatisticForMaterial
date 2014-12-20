package com.vzs.ls.application.ui;

import com.vzs.ls.application.input.pojo.InputContext;
import com.vzs.ls.application.logicExecutor.SingleRestaurantExecutorImpl;

/**
 * Created by ben.yao on 12/6/2014.
 */
public class CalaulateMain {
    public static void start(InputContext inputContext){
        SingleRestaurantExecutorImpl executor = new SingleRestaurantExecutorImpl(inputContext);
        executor.execute();
    }

	public static void main(String... args){
        start(new InputContext("/users/byao/Ben/personal/YUM/COS追踪工具制作/"));
	}
}
