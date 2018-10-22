package herman.transaction.analyser.entity;

import java.util.List;
import java.util.Set;

public class AllTransaction {
    private List<Transaction> paymentTransactions;
    private Set<String> reversalTransactionIds;

    public AllTransaction(List<Transaction> paymentTransactions, Set<String> reversalTransactionIds) {
        this.paymentTransactions = paymentTransactions;
        this.reversalTransactionIds = reversalTransactionIds;
    }

    public List<Transaction> getPaymentTransactions() {
        return paymentTransactions;
    }

    public void setPaymentTransactions(List<Transaction> paymentTransactions) {
        this.paymentTransactions = paymentTransactions;
    }

    public Set<String> getReversalTransactionIds() {
        return reversalTransactionIds;
    }

    public void setReversalTransactionIds(Set<String> reversalTransactionIds) {
        this.reversalTransactionIds = reversalTransactionIds;
    }
}
