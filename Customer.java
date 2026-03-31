import java.util.*;

public class Customer {
    String name;
    double balance;
    ArrayList<Transaction> transactions;

    public Customer(String name) {
        this.name = name;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(double amount, String type, String date) {
        Transaction t = new Transaction(amount, type, date);
        transactions.add(t);

        if (type.equalsIgnoreCase("credit")) {
            balance += amount;
        } else if (type.equalsIgnoreCase("debit")) {
            balance -= amount;
        }
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}
