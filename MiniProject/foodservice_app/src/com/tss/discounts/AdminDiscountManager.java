package com.tss.discounts;

import java.util.List;
import java.util.Scanner;

public class AdminDiscountManager {
	public class DiscountManager {
	    private Scanner scanner = new Scanner(System.in);
	    private DiscountContext context = DiscountContext.getInstance();

	    public void manageDiscounts() {
	        while (true) {
	            System.out.println("\n--- Discount Management ---");
	            System.out.println("1. View Active Discounts");
	            System.out.println("2. Add Flat Discount");
	            System.out.println("3. Add Percentage Discount");
	            System.out.println("4. Add Free Delivery Discount");
	            System.out.println("5. Remove Discount");
	            System.out.println("6. Clear All Discounts");
	            System.out.println("0. Back");
	            System.out.print("Choice: ");
	            int choice = Integer.parseInt(scanner.nextLine());

	            switch (choice) {
	                case 1 -> viewActive();
	                case 2 -> addFlat();
	                case 3 -> addPercent();
	                case 4 -> addFreeDelivery();
	                case 5 -> remove();
	                case 6 -> context.clearDiscounts();
	                case 0 -> { return; }
	                default -> System.out.println("Invalid choice.");
	            }
	        }
	    }

	    private void viewActive() {
	        List<IDiscountStrategy> discounts = context.getActiveDiscounts();
	        if (discounts.isEmpty()) {
	            System.out.println("No active discounts.");
	            return;
	        }
	        System.out.println("Active Discounts:");
	        for (int i = 0; i < discounts.size(); i++) {
	            System.out.println((i + 1) + ". " + discounts.get(i).getDescription());
	        }
	    }

	    private void addFlat() {
	        System.out.print("Enter flat discount amount: ₹");
	        double amount = Double.parseDouble(scanner.nextLine());
	        context.addDiscount(new FlatDiscount(amount));
	    }

	    private void addPercent() {
	        System.out.print("Enter discount percentage: ");
	        double percent = Double.parseDouble(scanner.nextLine());
	        context.addDiscount(new PercentageDiscount(percent));
	    }

	    private void addFreeDelivery() {
	        System.out.print("Minimum amount for free delivery: ₹");
	        double threshold = Double.parseDouble(scanner.nextLine());
	        System.out.print("Delivery charge to waive: ₹");
	        double deliveryFee = Double.parseDouble(scanner.nextLine());
	        context.addDiscount(new FreeDeliveryDiscount(threshold, deliveryFee));
	    }

	    private void remove() {
	        viewActive();
	        System.out.print("Enter index to remove: ");
	        int index = Integer.parseInt(scanner.nextLine()) - 1;
	        context.removeDiscount(index);
	    }
	}

}
