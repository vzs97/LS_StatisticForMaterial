package com.test;

import com.vzs.ls.application.utils.NormalUtil;
import org.junit.Test;

/**
 * Created by byao on 12/20/14.
 */
public class NormalUtilTest {

    @Test
    public void testPIIT(){
        String piit76201005201411 = NormalUtil.extractPIITRestruanNo("PIIT76201005201411.xls");
        System.out.println(piit76201005201411);

    }
}
