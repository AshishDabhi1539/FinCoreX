package com.tss.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.tss.customer.Customer;
import com.tss.util.DataStore;

public class CustomerService {

    private static final String CUSTOMER_FILE = "data/customers.ser";
    private Map<String, Customer> customers;

    public CustomerService() {
        File dir = new File("data");
        if (!dir.exists()) dir.mkdir();

        customers = DataStore.readFromFile(CUSTOMER_FILE);
        if (customers == null) customers = new HashMap<>();
    }

    public boolean registerCustomer(Customer customer) {
        if (customers.containsKey(customer.getName().toLowerCase())) return false;
        customers.put(customer.getName().toLowerCase(), customer);
        saveCustomers();
        return true;
    }

    public Customer authenticateCustomer(String name, String password) {
        for (Customer customer : customers.values()) {
            if (customer.getName().equalsIgnoreCase(name) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }


    public void updateCustomer(Customer customer) {
        customers.put(customer.getName().toLowerCase(), customer);
        saveCustomers();
    }


    @SuppressWarnings("unchecked")
    private void saveCustomers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/customers.ser"))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            System.err.println("[Error] Saving customers failed: " + e.getMessage());
        }
    }

    public Customer registerCustomer(Scanner scanner) {
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your age: ");
            int age = Integer.parseInt(scanner.nextLine());
//          System.out.print("Enter your gender: ");
//          String gender = scanner.nextLine();
//          System.out.print("Enter your email: ");
//          String email = scanner.nextLine();
//          System.out.print("Enter your phone number: ");
//          String number = scanner.nextLine();
            System.out.print("Enter your address: ");
            String address = scanner.nextLine();
            System.out.print("Set your password: ");
            String password = scanner.nextLine();

            Customer customer = new Customer(name, age, address, password);
            
            customers.put(name.toLowerCase(), customer);  // ✅ ADD TO MAP
            saveCustomers();                              // ✅ THEN SAVE

            return customer;
        } catch (Exception e) {
            System.out.println("❌ Registration error: " + e.getMessage());
            return null;
        }
    }

    
    private static CustomerService instance;

   

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

	
}
