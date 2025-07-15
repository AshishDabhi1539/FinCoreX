package com.tss.test;

import java.util.List;
import java.util.Scanner;

import com.tss.admin.Admin;
import com.tss.admin.AdminProxy;
import com.tss.customer.Customer;
import com.tss.customer.CustomerService;
import com.tss.discounts.AdminDiscountManager;
import com.tss.discounts.FreeDeliveryDiscount;
import com.tss.discounts.IDiscountStrategy;
import com.tss.model.cuisine.Cuisine;
import com.tss.model.delivery.DeliveryService;
import com.tss.model.menu.MenuManager;
import com.tss.service.CuisineService;
import com.tss.service.OrderService;

public class FoodAppTest {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String CUSTOMER_FILE = "data/customers.ser";

   
    public static void main(String[] args) {
        System.out.println("Welcome to the Food Ordering Console App!");

//        InitialCuisineSeeder.seedAllCuisines();
        while (true) {
            System.out.println("\nLOGIN MENU");
            System.out.println("1. Login as Admin");
            System.out.println("2. Register as Customer");
            System.out.println("3. Login as Customer");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> handleAdminLogin();
                case "2" -> CustomerService.registerCustomer(scanner, CUSTOMER_FILE);
                case "3" -> {
                    Customer customer = CustomerService.loginCustomer(scanner, CUSTOMER_FILE);
                    if (customer != null) handleCustomerFlow(customer);
                }
                case "4" -> {
                    System.out.println("Thank you for using the app!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void handleAdminLogin() {
        System.out.print("Username: ");
        String uname = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();

        Admin admin = Admin.getInstance("admin", "admin@123");
        AdminProxy proxy = new AdminProxy(admin);

        if (proxy.login(uname, pass)) {
            System.out.println("Welcome Admin!");

            while (true) {
                System.out.println("\nADMIN PANEL");
                System.out.println("1. Manage Menu");
                System.out.println("2. Manage Discounts");
                System.out.println("3. Assign Delivery Partner");
                System.out.println("4. Logout");

                System.out.print("Choose: ");
                String adminChoice = scanner.nextLine();

                switch (adminChoice) {
                    case "1" -> new MenuManager().openMenuManager(scanner);
                    case "2" -> new AdminDiscountManager().openDiscountPanel(scanner);
                    case "3" -> new DeliveryService().assignPartner(scanner);
                    case "4" -> {
                        System.out.println("Logged out.");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            }
        } else {
            System.out.println("Admin authentication failed.");
        }
    }

    private static void handleCustomerFlow(Customer customer) {
        CuisineService cuisineService = CuisineService.getInstance();
        OrderService orderService = new OrderService(scanner);

        System.out.println("\nHello, " + customer.getName());

        System.out.println("Available Cuisines:");
        List<String> cuisines = cuisineService.listAvailableCuisines();
        for (int i = 0; i < cuisines.size(); i++) {
            System.out.println((i + 1) + ". " + cuisines.get(i));
        }

        System.out.print("Select cuisine by number: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= cuisines.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        String selectedCuisine = cuisines.get(index);
        Cuisine cuisine = cuisineService.loadCuisine(selectedCuisine);

        // Set a sample discount strategy for customer
        IDiscountStrategy discountStrategy = new FreeDeliveryDiscount(); // can be changed dynamically

        orderService.placeOrder(customer, cuisine, discountStrategy);
    }
}
