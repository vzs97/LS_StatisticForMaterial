package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by byao on 12/26/14.
 */
public class DateUtil {
    public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat yyyyMMddmmss = new SimpleDateFormat("yyyy-MM-dd HH mm ss SSS");
    public static String getCurrentlyDateString(){
        return yyyyMMdd.format(new Date());
    }
    public static String getCurrentSSString(){
        return yyyyMMddmmss.format(new Date());
    }
}
