package core;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by byao on 2/15/15.
 */
public class LoopTest {
    @Test
    public void loopTest () {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 2001; i++ ) {
            sb.append(i).append("\r\n");
        }
        try {
            writeToFile ("/Users/byao/Ben/TEMP/vendorItemPackageIds.txt", sb.toString(), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeToFile(String filePath, String data, int position)
            throws IOException {
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        file.seek(position);

        file.write(data.getBytes());

        file.close();


    }

}
