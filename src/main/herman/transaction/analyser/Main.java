package herman.transaction.analyser;

import herman.transaction.analyser.entity.AllTransaction;
import herman.transaction.analyser.entity.AppResponse;
import herman.transaction.analyser.entity.Transaction;
import herman.transaction.analyser.services.InputDataProcessingService;
import herman.transaction.analyser.services.ReportService;

import java.util.List;

public class Main {
    public Main(String[] args) {
        if (args == null || args.length != 4) {
            System.out.println("Arguments is not valid, run the programs with 4 arguments:");
            System.out.println("transaction-analyser [CSV file path] [merchant name] [start date (dd/MM/yyyy HH:mm:ss)] [end date (dd/MM/yyyy HH:mm:ss)]");
            System.exit(1);
        }

        String filePath = args[0];
        String merchant = args[1];
        String fromDate = args[2];
        String toDate = args[3];

        System.out.println(System.getProperty("java.class.path"));
        System.out.println("----- Input -----");
        System.out.println("File path: " + filePath);
        System.out.println("Merchant: " + merchant);
        System.out.println("From date: " + fromDate);
        System.out.println("To date: " + toDate);
        System.out.println();

        try {
            AppResponse response = App.executeApp(filePath, merchant, fromDate, toDate);
            System.out.println("----- Output -----");
            System.out.println("Number of transactions = " + response.getTotalTransactions());
            System.out.println("Average Transaction Value = " + response.getAverageValue());
        } catch (Exception err) {
            System.out.println("Error when running application, Error message: " + err.getMessage());
//            err.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main(args);
    }
}
