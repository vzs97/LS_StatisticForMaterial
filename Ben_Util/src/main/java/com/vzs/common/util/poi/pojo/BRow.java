package com.vzs.common.util.poi.pojo;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ben.yao on 12/1/2014.
 */
@Retention(RUNTIME)
@Target({FIELD})
public @interface BRow {
	Class clazz();

}
