package com.vzs.common.util.poi.pojo;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ben.yao on 12/2/2014.
 */
@Retention(RUNTIME)
@Target({FIELD})
public @interface BCell {
	String column() default "";
	int row() default -1;
	TYPES types();
	boolean isStop() default false;
	String description() default "";

	public static enum TYPES{
		STRING,DATE,NUMERIC,INTEGER,BOOLEAN,PERCENT
	}
}
