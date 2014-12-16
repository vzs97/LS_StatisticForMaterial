package utils;

import org.springframework.util.StringUtils;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Created by byao on 12/16/14.
 */
public class BWorkbookUtil {
    public static int ToIndex(String columnName)
    {
        columnName = columnName.trim();
        if(StringUtils.isEmpty(columnName)){
            return -1;
        }
        Pattern pattern = Pattern.compile("[A-Z]+");
        if (!pattern.matcher(columnName.toUpperCase()).matches())
            throw new RuntimeException("invalid parameter");
        int index = 0;
        char[] chars = columnName.toUpperCase().toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            index += ((int)chars[i] - (int)'A' + 1) * (int)Math.pow(26, chars.length - i - 1);
        }
        return index - 1;
    }

    public static void mkdirsIfNotExist(String filePath){
        File directory = new File(filePath);
        if(!directory.exists()){
            directory.mkdirs();
        }
    }
}
