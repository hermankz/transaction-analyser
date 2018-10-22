package herman.transaction.analyser.services;

import org.junit.Assert;
import org.junit.Test;

import static herman.transaction.analyser.services.ReportService.roundUp;

public class ReportServiceTest {
    @Test
    public void testRoundUpReturnCorrectDataWithValidParams() {
        double result = ReportService.roundUp(2.2456777556, 2);
        boolean check = result == 2.25;
        Assert.assertTrue(check);
    }

    //TODO more tests
}
