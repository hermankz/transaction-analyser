package herman.transaction.analyser.services;

import herman.transaction.analyser.entity.AllTransaction;
import herman.transaction.analyser.entity.Transaction;
import herman.transaction.analyser.enums.TransactionColumn;
import herman.transaction.analyser.enums.TransactionType;
import herman.transaction.analyser.utils.DateUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputDataProcessingService {
    public static AllTransaction processCsvFile(String filePath) throws Exception {
        List<Transaction> paymentTransactions = new ArrayList<>();
        Set<String> reversalTransactions = new HashSet<>();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        int rowIdx = 0;
        String line;
        while ((line = br.readLine()) != null) {
            String[] row = line.split(",");

            // exclude first row if it's a Column Title
            if (rowIdx == 0 && isTitleRow(row)) {
                rowIdx++;
                continue;
            }

            try {
                Transaction t = convertToTransaction(row);
                if (t.getType() == TransactionType.Reversal) {
                    // assume do not need to keep Reversal transaction details, just keep the related transaction id
                    reversalTransactions.add(t.getRelatedTransactionId());
                } else {
                    paymentTransactions.add(t);
                }
            } catch (Exception err) {
                System.out.println("Unable to process data on line: " + (rowIdx+1) + ", Error message: " + err.getMessage());
//                err.printStackTrace();
            }
            rowIdx++;
        }

        return new AllTransaction(paymentTransactions, reversalTransactions);
    }

    public static boolean isTitleRow(String[] row) {
        return isEqualColumn(row, TransactionColumn.Id) &&
                isEqualColumn(row, TransactionColumn.Date) &&
                isEqualColumn(row, TransactionColumn.Amount) &&
                isEqualColumn(row, TransactionColumn.Merchant);
    }

    public static boolean isEqualColumn(String[] row, TransactionColumn tCol) {
        return row[tCol.getIndex()].equalsIgnoreCase(tCol.getName());
    }

    public static Transaction convertToTransaction(String[] row) throws Exception {
        Transaction t = new Transaction();
        t.setId(row[TransactionColumn.Id.getIndex()]);
        t.setDate(DateUtil.convertToDate(row[TransactionColumn.Date.getIndex()]));
        t.setAmount(Double.parseDouble(row[TransactionColumn.Amount.getIndex()]));
        t.setMerchant(row[TransactionColumn.Merchant.getIndex()]);

        TransactionType type = convertToTransactionType(row[TransactionColumn.Type.getIndex()]);
        if (type == null) {
            throw new Exception("Transaction type is unknown. Type = " + row[TransactionColumn.Type.getIndex()]);
        }
        t.setType(type);

        // some transaction does not have related transaction
        if (t.getType() == TransactionType.Reversal && row.length > TransactionColumn.RelatedTransaction.getIndex()) {
            t.setRelatedTransactionId(row[TransactionColumn.RelatedTransaction.getIndex()]);
        }

        return t;
    }

    public static TransactionType convertToTransactionType(String type) {
        if (TransactionType.Payment.getName().equalsIgnoreCase(type)) {
            return TransactionType.Payment;
        } else if (TransactionType.Reversal.getName().equalsIgnoreCase(type)) {
            return TransactionType.Reversal;
        }
        return null;
    }
}
