import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Manager manager = new Manager();

        manager.loadData(); // Load saved data

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Transaction");
            System.out.println("3. View Customers");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter customer name: ");
                    String name = sc.nextLine();
                    manager.addCustomer(name);
                    break;

                case 2:
                    System.out.print("Enter customer name: ");
                    String custName = sc.nextLine();
                    Customer c = manager.findCustomer(custName);

                    if (c == null) {
                        System.out.println("Customer not found!");
                        break;
                    }

                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter type (credit/debit): ");
                    String type = sc.nextLine();

                    System.out.print("Enter date: ");
                    String date = sc.nextLine();

                    c.addTransaction(amt, type, date);
                    manager.saveData(); // Save after transaction

                    System.out.println("Transaction added.");
                    break;

                case 3:
                    manager.showAllCustomers();
                    break;

                case 4:
                    System.out.print("Enter customer name: ");
                    Customer cust = manager.findCustomer(sc.nextLine());

                    if (cust != null) {
                        cust.showTransactions();
                    } else {
                        System.out.println("Customer not found!");
                    }
                    break;

                case 5:
                    manager.saveData(); // Final save
                    sc.close();
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
