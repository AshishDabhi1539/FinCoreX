package com.tss.service;

import com.tss.customer.Customer;
import com.tss.model.cuisine.Cuisine;
import com.tss.model.menu.BaseMenuItem;
import com.tss.model.menu.DiscountedItemDecorator;
import com.tss.model.delivery.DeliveryPartner;
import com.tss.model.delivery.DeliveryProxy;
import com.tss.discounts.IDiscountStrategy;

import java.util.*;

public class OrderService {
    private final Scanner scanner;

    public OrderService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void placeOrder(Customer customer, Cuisine cuisine, IDiscountStrategy discountStrategy) {
        if (cuisine == null) {
            System.out.println("❌ Invalid cuisine selection.");
            return;
        }

        Map<BaseMenuItem, Integer> cart = new LinkedHashMap<>();
        cuisine.printMenu();

        while (true) {
            System.out.print("\nEnter Item ID to add to cart (or type 'done'): ");
            String input = scanner.nextLine();
            if ("done".equalsIgnoreCase(input)) break;

            BaseMenuItem item = cuisine.getItemById(input);
            if (item == null) {
                System.out.println("❌ Invalid item ID.");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = Integer.parseInt(scanner.nextLine());
            cart.put(item, qty);
        }

        if (cart.isEmpty()) {
            System.out.println("🛒 No items ordered.");
            return;
        }

        printReceipt(customer, cart, discountStrategy);
    }

    private void printReceipt(Customer customer, Map<BaseMenuItem, Integer> cart, IDiscountStrategy discountStrategy) {
        System.out.println("\n🧾 Order Receipt for: " + customer.getName());
        double total = 0;

        for (BaseMenuItem item : cart.keySet()) {
            int qty = cart.get(item);
            double itemTotal = item.getPrice() * qty;
            System.out.printf("%-25s x%-2d = ₹%.2f%n", item.getName(), qty, itemTotal);
            total += itemTotal;
        }

        double discountedTotal = discountStrategy.applyDiscount(total);
        double discount = total - discountedTotal;

        System.out.printf("Subtotal: ₹%.2f%n", total);
        System.out.printf("Discount Applied: -₹%.2f%n", discount);
        System.out.printf("Total Payable: ₹%.2f%n", discountedTotal);

        DeliveryPartner partner = new DeliveryPartner("AUTO", "Default Area");
        new DeliveryProxy(partner).assign("Order of ₹" + discountedTotal);

        System.out.println("✅ Order placed successfully!\n");
    }
}
