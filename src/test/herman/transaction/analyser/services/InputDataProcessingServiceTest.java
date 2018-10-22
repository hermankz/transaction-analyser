package herman.transaction.analyser.services;

import herman.transaction.analyser.enums.TransactionType;
import org.junit.Assert;
import org.junit.Test;

public class InputDataProcessingServiceTest {
    @Test
    public void testIsTitleRowReturnTrueWithValidTitleData() {
        String[] strings = new String[]{"ID", "Date", "Amount", "Merchant"};
        boolean result = InputDataProcessingService.isTitleRow(strings);
        Assert.assertTrue(result);
    }

    @Test
    public void testIsTitleRowReturnFalseWithInvalidData() {
        String[] strings = new String[]{"ID", "Date", "Amount", "Kwik-E-Mart"};
        boolean result = InputDataProcessingService.isTitleRow(strings);
        Assert.assertFalse(result);
    }

    @Test
    public void testIsTitleRowReturnFalseWithValidContentData() {
        String[] strings = new String[]{"WLMFRDGD", "20/08/2018 12:45:33", "59.99", "Kwik-E-Mart", "PAYMENT"};
        boolean result = InputDataProcessingService.isTitleRow(strings);
        Assert.assertFalse(result);
    }

    @Test
    public void testconvertToTransactionTypeReturnCorrectTypeWithValidData() {
        TransactionType type = InputDataProcessingService.convertToTransactionType("PAYMENT");
        Assert.assertEquals(TransactionType.Payment, type);
    }

    @Test
    public void testconvertToTransactionTypeReturnNullTypeWithInvalidData() {
        TransactionType type = InputDataProcessingService.convertToTransactionType("TEST-TYPE");
        Assert.assertNull(type);
    }

    //TODO more tests
}
