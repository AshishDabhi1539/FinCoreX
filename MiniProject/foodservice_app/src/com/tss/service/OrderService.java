package com.tss.service;

import java.util.*;

import com.tss.customer.Customer;
import com.tss.discounts.DiscountContext;
import com.tss.model.cuisine.Cuisine;
import com.tss.model.delivery.DeliveryContext;
import com.tss.model.menu.BaseMenuItem;
import com.tss.model.order.Order;
import com.tss.model.printer.InvoicePrinter;
import com.tss.payment.PaymentService;
import com.tss.util.DataStore;

public class OrderService {
    private static OrderService instance;
    private static final String ORDER_FILE = "data/orders.ser";
    private final List<Order> orders;
    private final Map<Customer, List<BaseMenuItem>> cartMap = new HashMap<>();

    public OrderService() {
        List<Order> loaded = DataStore.readFromFile(ORDER_FILE);
        orders = (loaded != null) ? loaded : new ArrayList<>();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public void showCustomerDashboard(Customer customer, Scanner scanner) {
        while (true) {
            System.out.println("\n========= Customer Dashboard =========");
            System.out.println("1. Browse & Add Food from Cuisine");
            System.out.println("2. View/Edit Cart");
            System.out.println("3. Checkout & Payment");
            System.out.println("4. Track Order");
//            System.out.println("5. Provide Feedback");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": browseCuisine(customer, scanner); break;
                case "2": viewEditCart(customer, scanner); break;
                case "3": checkout(customer, scanner); break;
                case "4": trackOrder(customer); break;
//                case "5": collectFeedback(customer, scanner); break;
                case "5":
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void browseCuisine(Customer customer, Scanner scanner) {
        System.out.println("Available cuisines:");
        CuisineService cuisineService = CuisineService.getInstance();
        List<String> cuisineList = cuisineService.getAllCuisineNames();

        for (int i = 0; i < cuisineList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, cuisineList.get(i));
        }

        System.out.print("Select cuisine to browse (0 to cancel): ");
        int choice = Integer.parseInt(scanner.nextLine().trim());
        if (choice == 0 || choice > cuisineList.size()) {
            return;
        }

        String selectedCuisine = cuisineList.get(choice - 1);
        Cuisine cuisine = cuisineService.loadCuisine(selectedCuisine);
        if (cuisine == null || cuisine.getMenuItems().isEmpty()) {
            System.out.println("⚠️ No items found in this cuisine.");
            return;
        }

        List<BaseMenuItem> items = cuisine.getMenuItems();
        List<BaseMenuItem> cart = cartMap.getOrDefault(customer, new ArrayList<>());

        while (true) {
            System.out.println("\nMenu for " + selectedCuisine + ":");
            for (int i = 0; i < items.size(); i++) {
                BaseMenuItem item = items.get(i);
                System.out.printf("%d. [%s] %s - ₹%.2f | %s\n", i + 1, item.getId(), item.getName(), item.getPrice(), item.getDescription());
            }
            System.out.print("Enter item number to add (0 to finish): ");
            int itemChoice = Integer.parseInt(scanner.nextLine().trim());
            if (itemChoice == 0) break;
            if (itemChoice < 1 || itemChoice > items.size()) {
                System.out.println("Invalid item number.");
                continue;
            }
            cart.add(items.get(itemChoice - 1));
            System.out.println("Item added to cart.");
        }

        cartMap.put(customer, cart);
    }

    private void viewEditCart(Customer customer, Scanner scanner) {
        List<BaseMenuItem> cart = cartMap.getOrDefault(customer, new ArrayList<>());
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("Your cart items:");
        for (int i = 0; i < cart.size(); i++) {
            BaseMenuItem item = cart.get(i);
            System.out.printf("%d. %s — ₹%.2f\n", i + 1, item.getName(), item.getPrice());
        }

        System.out.print("Enter item number to remove (0 to skip): ");
        int idx = Integer.parseInt(scanner.nextLine());
        if (idx >= 1 && idx <= cart.size()) {
            cart.remove(idx - 1);
            System.out.println("Item removed.");
        }
        cartMap.put(customer, cart);
    }

    private void checkout(Customer customer, Scanner scanner) {
        List<BaseMenuItem> cart = cartMap.getOrDefault(customer, new ArrayList<>());
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        double totalAmount = calculateCartTotal(cart);
        double finalAmount = DiscountContext.getInstance().applyAllDiscounts(totalAmount);
        String partner = new DeliveryContext().assignRandomPartner();
        String paymentMode = PaymentService.processPayment(scanner, finalAmount);

        Order order = new Order(customer, new ArrayList<>(cart), totalAmount);
        order.setAssignedDeliveryPartner(partner);
        orders.add(order);
        DataStore.saveToFile(orders, ORDER_FILE);

        InvoicePrinter.printInvoice(order, finalAmount, paymentMode, partner);
        System.out.println("Order placed successfully!");

        cart.clear();
        cartMap.put(customer, cart);
    }

    private double calculateCartTotal(List<BaseMenuItem> cart) {
        return cart.stream().mapToDouble(BaseMenuItem::getPrice).sum();
    }

    private void trackOrder(Customer customer) {
        boolean found = false;
        for (Order order : orders) {
            if (order.getCustomer().equals(customer)) {
                System.out.printf("Order #%s — Status: %s\n", order.getOrderId(), order.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No orders found.");
        }
    }

//    private void collectFeedback(Customer customer, Scanner scanner) {
//        System.out.print("✍Enter feedback for your last order: ");
//        String feedback = scanner.nextLine();
//        System.out.println("Thanks for your feedback!");
//    }

    public static void viewAllOrders() {
        List<Order> all = getInstance().orders;
        if (all.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        for (Order o : all) {
            System.out.printf("ID: %s | Customer: %s | ₹%.2f | Status: %s\n",
                    o.getOrderId(), o.getCustomer().getName(), o.getTotalAmount(), o.getStatus());
        }
    }
}
