package utils;

import java.io.File;

/**
 * Created by byao on 12/28/14.
 */
public class FileUtils {
    public static boolean isExists(String filePath){
        return new File(filePath).exists();
    }
}
