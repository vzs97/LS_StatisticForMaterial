package com.vzs.ls.application.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by byao on 12/13/14.
 */
public class NormalUtil {
    static Pattern pattern = Pattern.compile("^(.*?)\\d.*$");
    static Pattern piitPattern = Pattern.compile("^PIIT76(\\d*?)\\d{6}\\..*$");
    public static String extractResturantName(String fileName){
        Matcher matcher = pattern.matcher(fileName);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
    public static FileFilter xlsFileFilter = new FileFilter(){

        @Override
        public boolean accept(File file) {
            if(file.isFile() && (file.getName().endsWith("xls") || file.getName().endsWith("xlsx")) && !file.getName().startsWith("~$")){
                return true;
            }
            return false;
        }
    };
    public static String extractPIITRestruanNo(String fileName){
        //PIIT76201005201411

        Matcher matcher = piitPattern.matcher(fileName);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
