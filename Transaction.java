public class Transaction {
    double amount;
    String type;
    String date;

    public Transaction(double amount, String type, String date) {
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    @Override
    public String toString() {
        return type + "," + amount + "," + date;
    }
}
