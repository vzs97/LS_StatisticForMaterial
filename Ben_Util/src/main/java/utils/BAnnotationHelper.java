package utils;

import com.vzs.common.util.poi.pojo.BWorkbook;

/**
 * Created by ben.yao on 12/6/2014.
 */
public class BAnnotationHelper {
	public static void isAnnotationPresent(Class clazz,Class annotionClazz, String runTimeMessage){
		if(!clazz.isAnnotationPresent(annotionClazz)){
			throw new IllegalArgumentException(runTimeMessage);
		}

	}
}
