package utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
    public static void setValues(Object obj, Field field, Object... values){
        String fieldName = field.getName();
        String setMethodName;
        if(fieldName.startsWith("is")) {
            setMethodName = "set" + fieldName.substring(2);
        }else {
            setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
        try {
            Method setMethod = obj.getClass().getMethod(setMethodName, new Class[] { field.getType() });
            setMethod.invoke(obj,values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getValue(Object obj,Field field){
        String fieldName = field.getName();
        return getValue(obj,fieldName);

    }
    public static Object getValue(Object obj,String fieldName){
        String getMethodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
        try {
            Method getMethod=obj.getClass().getMethod(getMethodName, new Class[]{});
            return getMethod.invoke(obj,new Object[]{});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }
}
