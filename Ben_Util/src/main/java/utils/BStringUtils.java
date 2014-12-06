package utils;

import java.text.DecimalFormat;

/**
 * Created by ben.yao on 12/6/2014.
 */
public class BStringUtils {
	public static String toString(Object obj){
		if(obj instanceof Number){
			return new DecimalFormat("#").format(obj);
		}
		return obj.toString();
	}
	public static Boolean toBoolean(Object obj){
		return "Y".equalsIgnoreCase(obj.toString())?Boolean.TRUE:Boolean.FALSE;
	}
}
