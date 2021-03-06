package com.vzs.common.util.poi.pojo;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.*;

/**
 * Created by ben.yao on 12/1/2014.
 */
@Retention(RUNTIME)
@Target({TYPE})
public @interface BWorkbook {
	String description() default "";
}
