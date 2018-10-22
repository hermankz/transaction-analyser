package herman.transaction.analyser.services;

import herman.transaction.analyser.entity.AllTransaction;
import herman.transaction.analyser.entity.Transaction;
import herman.transaction.analyser.utils.DateUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ReportService {
    public static List<Transaction> getAverageTransactionByMerchantAndDateRange(AllTransaction transactions, String merchant, String strFromDate, String strToDate) throws Exception {
        Date fromDate = DateUtil.convertToDate(strFromDate);
        Date toDate = DateUtil.convertToDate(strToDate);
        return getAverageTransactionByMerchantAndDateRange(transactions, merchant, fromDate, toDate);
    }

    public static List<Transaction> getAverageTransactionByMerchantAndDateRange(AllTransaction transactions, String merchant, Date fromDate, Date toDate) {
        List<Transaction> reportTransactions = new ArrayList<>();

        List<Transaction> paymentTransactions = transactions.getPaymentTransactions();
        Set<String> reversalTransactions = transactions.getReversalTransactionIds();

        for (Transaction t : paymentTransactions) {
            if (!t.getMerchant().equalsIgnoreCase(merchant)) {
                continue;
            }

            Date transactionDate = t.getDate();
            // assume data is ordered by date ascending
            // check if transaction date is after toDate then no need to check next transaction
            if (transactionDate.after(toDate)) {
                break;
            }

            if (DateUtil.isDateBetween(transactionDate, fromDate, toDate)) {
                // check not contains in Reversal transactions
                if (!reversalTransactions.contains(t.getId())) {
                    reportTransactions.add(t);
                } else {
                    reversalTransactions.remove(t.getId());
                }
            }
        }

        return reportTransactions;
    }

    public static double calcAverageAmount(List<Transaction> transactions) {
        double total = 0;
        for (Transaction t : transactions) {
             total += t.getAmount();
        }

        double average = total / transactions.size();
        return roundUp(average, 2);
    }

    public static double roundUp(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
