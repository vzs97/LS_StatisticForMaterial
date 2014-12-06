package com.vzs.ls.application.ui;

import com.vzs.ls.application.input.pojo.InputContext;
import com.vzs.ls.application.logicExecutor.SingleRestaurantExecutorImpl;

/**
 * Created by ben.yao on 12/6/2014.
 */
public class UIMain {
	public static void main(String... args){
		SingleRestaurantExecutorImpl executor = new SingleRestaurantExecutorImpl(new InputContext("D:\\Ben\\personal\\COS追踪工具制作\\"));
		executor.execute();
	}
}
