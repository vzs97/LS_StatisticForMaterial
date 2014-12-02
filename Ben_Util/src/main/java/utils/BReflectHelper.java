package utils;

import java.lang.reflect.Field;

/**
 * Created by vzs on 2014/12/2.
 */
public class BReflectHelper {
    public static <T> T newInstance(Class t){
        try {
            return (T)t.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't new instance for class " + t.getClass().getName());
        }
    }
    public static void setValues(Object obj, Field field, Object... value){
        String fieldName = field.getName();
        String setMethod = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);

    }
}
