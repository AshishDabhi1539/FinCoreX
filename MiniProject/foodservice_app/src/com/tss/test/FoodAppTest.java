package com.tss.test;

import java.util.Scanner;

import com.tss.admin.AdminPanel;
import com.tss.customer.Customer;
import com.tss.service.CustomerService;
import com.tss.service.OrderService;

public class FoodAppTest {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerService customerService = CustomerService.getInstance();

    public static void main(String[] args) {
        System.out.println("Welcome to Food Ordering Console App!");

        while (true) {
            System.out.println("\n========= Main Menu =========");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. New Customer Registration");
            System.out.println("4. Exit");
            System.out.print("👉 Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    handleAdminLogin();
                    break;
                case 2:
                    handleCustomerLogin();
                    break;
                case 3:
                    handleCustomerRegistration();
                    break;
                case 4:
                    System.out.println("👋 Thank you! Exiting...");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private static void handleAdminLogin() {
        System.out.print("👤 Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("🔐 Enter admin password: ");
        String password = scanner.nextLine();

        // Hardcoded admin credentials
        if (username.equalsIgnoreCase("admin") && password.equals("admin123")) {
            System.out.println("✅ Admin login successful!");
            AdminPanel adminPanel = new AdminPanel(scanner);
            adminPanel.showAdminMenu();
        } else {
            System.out.println("❌ Invalid admin credentials.");
        }
    }

    private static void handleCustomerLogin() {
        System.out.print("👤 Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("🔐 Enter password: ");
        String password = scanner.nextLine();

        Customer customer = customerService.authenticateCustomer(name, password);
        if (customer != null) {
            System.out.println("✅ Welcome, " + customer.getName() + "!");
            OrderService.placeOrder(customer, scanner);
        } else {
            System.out.println("❌ Invalid customer credentials.");
        }
    }

    private static void handleCustomerRegistration() {
        Customer newCustomer = customerService.registerCustomer(scanner);
        if (newCustomer != null) {
            System.out.println("✅ Registration successful. You can now log in.");
        } else {
            System.out.println("❌ Registration failed.");
        }
    }

}
