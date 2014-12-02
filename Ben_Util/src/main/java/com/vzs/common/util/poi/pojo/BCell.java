package com.vzs.common.util.poi.pojo;

/**
 * Created by ben.yao on 12/2/2014.
 */
public @interface BCell {
	int column() default -1;
	int row() default -1;
	TYPES types();

	public static enum TYPES{
		STRING,DATE,DOUBLE,INTEGER
	}
}
