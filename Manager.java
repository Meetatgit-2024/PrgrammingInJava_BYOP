import java.util.*;
import java.io.*;

public class Manager {
    ArrayList<Customer> customers;
    private final String FILE_NAME = "data.txt";

    public Manager() {
        customers = new ArrayList<>();
    }

    //  Load data from file
    public void loadData() {
        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Customer currentCustomer = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Customer:")) {
                    String name = line.substring(9);
                    currentCustomer = new Customer(name);
                    customers.add(currentCustomer);
                } else if (!line.trim().isEmpty()) {
                    String[] parts = line.split(",");

                    String type = parts[0];
                    double amount = Double.parseDouble(parts[1]);
                    String date = parts[2];

                    if (currentCustomer != null) {
                        currentCustomer.addTransaction(amount, type, date);
                    }
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    //  Save data to file
    public void saveData() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));

            for (Customer c : customers) {
                bw.write("Customer:" + c.name);
                bw.newLine();

                for (Transaction t : c.transactions) {
                    bw.write(t.toString());
                    bw.newLine();
                }
            }

            bw.close();

        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    //  Add Customer
    public void addCustomer(String name) {
        if (findCustomer(name) != null) {
            System.out.println("Customer already exists!");
            return;
        }

        customers.add(new Customer(name));
        saveData();
        System.out.println("Customer added successfully.");
    }

    //  Find Customer
    public Customer findCustomer(String name) {
        for (Customer c : customers) {
            if (c.name.equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    //  Show All Customers
    public void showAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        for (Customer c : customers) {
            System.out.println(c.name + " | Balance: ₹" + c.balance);
        }
    }
}
