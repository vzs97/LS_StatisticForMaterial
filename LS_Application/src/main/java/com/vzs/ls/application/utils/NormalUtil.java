package com.vzs.ls.application.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by byao on 12/13/14.
 */
public class NormalUtil {
    static Pattern pattern = Pattern.compile("^(.*?)\\d.*$");
    public static String extractResturantName(String fileName){
        Matcher matcher = pattern.matcher(fileName);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
