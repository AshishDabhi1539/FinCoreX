package com.tss.facade;

import java.util.Scanner;

import com.tss.admin.Admin;
import com.tss.admin.AdminPanel;
import com.tss.customer.Customer;
import com.tss.service.AdminService;
import com.tss.service.CustomerService;
import com.tss.service.OrderService;

public class FoodAppFacade {
    private final Scanner scanner;
    private final CustomerService customerService = CustomerService.getInstance();
    private final AdminService adminService = AdminService.getInstance();

    public FoodAppFacade(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startApp() {
        while (true) {
            printMainMenu();
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> adminLoginFlow();
                case "2" -> customerLoginFlow();
                case "3" -> customerRegistrationFlow();
                case "4" -> exitApp();
                default -> System.out.println("❌ Invalid input. Try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n========= Main Menu =========");
        System.out.println("1. Admin Login");
        System.out.println("2. Customer Login");
        System.out.println("3. New Customer Registration");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void adminLoginFlow() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        Admin admin = adminService.authenticate(username, password);
        if (admin != null) {
            System.out.println("Admin login successful.");
            AdminPanel adminPanel = new AdminPanel(scanner);
            adminPanel.showAdminMenu(); 
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private void customerLoginFlow() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Customer customer = customerService.authenticateCustomer(name, password);
        if (customer != null) {
            System.out.println("Welcome, " + customer.getName() + "!");
            new OrderService().showCustomerDashboard(customer, scanner); 
        } else {
            System.out.println("Invalid customer credentials.");
        }
    }

    private void customerRegistrationFlow() {
        Customer newCustomer = customerService.registerCustomer(scanner);
        if (newCustomer != null) {
            System.out.println("✅ Registration successful. You can now log in.");
        } else {
            System.out.println("❌ Registration failed.");
        }
    }

    private void exitApp() {
        System.out.println("Thank you for using Food Ordering App. Goodbye!");
        System.exit(0);
    }
}
