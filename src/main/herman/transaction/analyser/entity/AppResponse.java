package herman.transaction.analyser.entity;

public class AppResponse {
    private int totalTransactions;
    private double averageValue;

    public AppResponse(int totalTransactions, double averageValue) {
        this.totalTransactions = totalTransactions;
        this.averageValue = averageValue;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public double getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(double averageValue) {
        this.averageValue = averageValue;
    }
}
