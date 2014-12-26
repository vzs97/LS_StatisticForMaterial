package com.test;

import com.vzs.common.util.log.SingleThreadLogUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by byao on 12/13/14.
 */
public class Main {
    public static void main(String ... args){
        Pattern pattern = Pattern.compile("^(.*?)\\d.*$");
        String fileName="上海南丹路店2014年11月菜品销售统计表.xls";
        Matcher matcher = pattern.matcher(fileName);
        SingleThreadLogUtil.log(matcher.find());
        SingleThreadLogUtil.log(matcher.group(1));
    }
}
