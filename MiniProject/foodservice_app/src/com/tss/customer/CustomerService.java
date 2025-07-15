package com.tss.customer;

import java.util.Map;
import java.util.Scanner;

import com.tss.util.DataStore;



public class CustomerService {
    private static CustomerService instance;
    private static final String CUSTOMER_FILE = "customers.ser";
    private static Map<String, Customer> customerMap;

    private CustomerService() {
        customerMap = DataStore.loadCustomerData(CUSTOMER_FILE);
    }

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public static void registerCustomer(Scanner scanner, String filePath) {
        System.out.println("\nRegister New Customer:");
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        if (customerMap.containsKey(name)) {
            System.out.println("Customer already exists. Try logging in.");
            return;
        }

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(name, password, age, gender, email, phone, address);
        customerMap.put(name, customer);
        DataStore.saveCustomerData(CUSTOMER_FILE, customerMap);

        System.out.println("Registration successful.");
    }

    public static Customer loginCustomer(Scanner scanner, String filePath) {
        Map<String, Customer> customerMap = DataStore.loadCustomerData(filePath);

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Customer customer = customerMap.get(name);
        if (customer != null && customer.getPassword().equals(password)) {
            System.out.println("Login successful. Welcome " + customer.getName());
            return customer;
        } else {
            System.out.println("Invalid credentials.");
            return null;
        }
    }


    public Customer getCustomerByName(String name) {
        return customerMap.get(name);
    }
}
