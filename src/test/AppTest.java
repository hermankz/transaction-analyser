import herman.transaction.analyser.App;
import herman.transaction.analyser.Main;
import herman.transaction.analyser.entity.AppResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class AppTest {
    @Test
    public void testAppWithValidParams() {
        File file = new File(getClass().getClassLoader().getResource("test-valid.csv").getFile());

        String args[] = new String[] {file.getAbsolutePath(), "Kwik-E-Mart", "20/08/2018 12:00:00", "20/08/2018 13:00:00"};

        AppResponse response = null;
        try {
            response = App.executeApp(args[0], args[1], args[2], args[3]);
        } catch (Exception err) {
        }

        Double expectedAverage = new Double(59.99);
        Assert.assertEquals(1, response.getTotalTransactions());
        Assert.assertEquals(expectedAverage, new Double(response.getAverageValue()));
    }

    //TODO more tests
}
