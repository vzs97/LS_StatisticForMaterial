package com.vzs.common.util.poi.pojo;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by byao on 12/15/14.
 */

@Retention(RUNTIME)
@Target({FIELD})
public @interface BStyle {
    Class styleHanlde();
    BStyleMethod method();
    String refereceObj();


}
