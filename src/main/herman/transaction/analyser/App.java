package herman.transaction.analyser;

import herman.transaction.analyser.entity.AllTransaction;
import herman.transaction.analyser.entity.AppResponse;
import herman.transaction.analyser.entity.Transaction;
import herman.transaction.analyser.services.InputDataProcessingService;
import herman.transaction.analyser.services.ReportService;

import java.util.List;

public class App {
    public static AppResponse executeApp(String filePath, String merchant, String fromDate, String toDate) throws Exception {
        AllTransaction transactions = InputDataProcessingService.processCsvFile(filePath);
        List<Transaction> reportTransactions = ReportService.getAverageTransactionByMerchantAndDateRange(transactions, merchant, fromDate, toDate);

        return new AppResponse(reportTransactions.size(), ReportService.calcAverageAmount(reportTransactions));
    }
}
