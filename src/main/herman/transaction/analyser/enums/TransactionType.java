package herman.transaction.analyser.enums;

public enum TransactionType {
    Payment("PAYMENT"),
    Reversal("REVERSAL"),
    ;

    private String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
