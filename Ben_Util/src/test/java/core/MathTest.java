package core;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by byao on 2/3/15.
 */
public class MathTest {
    @Test
    public void roundupTest(){
        BigDecimal salePrice = new BigDecimal(100);
        Integer unitCount = 3;
        BigDecimal divide = salePrice.divide(new BigDecimal(unitCount), RoundingMode.CEILING);
        Assert.assertTrue(divide.equals(new BigDecimal(34)));

        divide = salePrice.divide(new BigDecimal(10), RoundingMode.CEILING);
        Assert.assertTrue(divide.intValue() == 10);

    }
}
