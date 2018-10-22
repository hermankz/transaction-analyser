package herman.transaction.analyser.enums;

public enum TransactionColumn {
    Id(0, "ID"),
    Date(1, "Date"),
    Amount(2, "Amount"),
    Merchant(3, "Merchant"),
    Type(4, "Type"),
    RelatedTransaction(5, "Related Transaction"),
    ;

    private int index;
    private String name;

    TransactionColumn(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
