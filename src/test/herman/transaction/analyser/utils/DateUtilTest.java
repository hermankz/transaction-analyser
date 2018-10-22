package herman.transaction.analyser.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DateUtilTest {
    @Test
    public void testConvertToDateReturnCorrectWithValidParam() {
        Date date = null;
        try {
            date = DateUtil.convertToDate("20/08/2018 12:00:00");
        } catch (Exception err) {
        }

        Assert.assertNotNull(date);
    }

    @Test
    public void testConvertToDateReturnCorrectWithInvalidParam() {
        Date date = null;
        try {
            date = DateUtil.convertToDate("20/08/2018 00:00");
        } catch (Exception err) {
        }

        Assert.assertNull(date);
    }

    //TODO more tests
}
