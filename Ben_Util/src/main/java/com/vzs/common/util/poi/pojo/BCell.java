package com.vzs.common.util.poi.pojo;

/**
 * Created by ben.yao on 12/2/2014.
 */
public @interface BCell {
	String column() default "";
	int row() default -1;
	TYPES types();
	String description() default "";

	public static enum TYPES{
		STRING,DATE,DOUBLE,INTEGER,BOOLEAN,PERCENT
	}
}
