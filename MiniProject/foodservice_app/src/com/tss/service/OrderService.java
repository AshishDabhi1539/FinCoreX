package com.tss.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	private static final String ORDER_FILE = "data/orders.ser";
	private static List<Order> orders = new ArrayList<>();

	static {
		List<Order> loaded = DataStore.readFromFile(ORDER_FILE);
		if (loaded != null) {
			orders = loaded;
		}
	}

	public static void placeOrder(Customer customer, Scanner scanner) {

		List<String> availableCuisines = CuisineService.listAvailableCuisines();
		if (availableCuisines.isEmpty()) {
			System.out.println("No cuisines available.");
			return;
		}

		System.out.println("\nAvailable Cuisines:");
		for (String c : availableCuisines) {
			System.out.println("- " + c);
		}

		System.out.print("Enter cuisine name to order: ");
		String cuisineName = scanner.nextLine().trim();

		Cuisine cuisine = CuisineService.getInstance().loadCuisine(cuisineName.toLowerCase());
		if (cuisine == null) {
			System.out.println("Cuisine not found.");
			return;
		}

		List<BaseMenuItem> menuItems = MenuManager.getMenuItems(cuisine);
		if (menuItems == null || menuItems.isEmpty()) {
			System.out.println("No items found for this cuisine.");
			return;
		}

		System.out.println("\nMenu for " + cuisine.getName() + ":");
		for (int i = 0; i < menuItems.size(); i++) {
			System.out.println((i + 1) + ". " + menuItems.get(i));
		}

		List<BaseMenuItem> selectedItems = new ArrayList<>();
		while (true) {
			System.out.print("Enter item number to add (0 to finish): ");
			int choice = Integer.parseInt(scanner.nextLine());

			if (choice == 0)
				break;
			if (choice < 1 || choice > menuItems.size()) {
				System.out.println("Invalid choice.");
				continue;
			}

			selectedItems.add(menuItems.get(choice - 1));
		}

		if (selectedItems.isEmpty()) {
			System.out.println("No items selected.");
			return;
		}

		double total = selectedItems.stream().mapToDouble(BaseMenuItem::getPrice).sum();

		DiscountContext discountContext = new DiscountContext();
		discountContext.autoSetStrategy(total);
		double discountedTotal = discountContext.applyDiscount(total);

		DeliveryContext deliveryContext = new DeliveryContext();
		String partner = deliveryContext.assignRandomPartner();

		Order order = new Order(customer, selectedItems, total);
		order.setAssignedDeliveryPartner(partner);
		orders.add(order);

		DataStore.saveToFile(orders, ORDER_FILE);

		String paymentMode = PaymentService.processPayment(scanner, discountedTotal);
		InvoicePrinter.printInvoice(order, discountedTotal, paymentMode, partner);

		File orderDir = new File("data/orders/");
		if (!orderDir.exists())
			orderDir.mkdirs();
		String orderPath = "data/orders/" + order.getOrderId() + ".ser";
		DataStore.saveToFile(order, orderPath);

		System.out.println("Order placed successfully!");

		System.out.println("Thank you for shopping with us!");
		System.exit(0);

	}

	public static void viewAllOrders() {
		if (orders.isEmpty()) {
			System.out.println("No orders found.");
			return;
		}

		System.out.println("\nAll Orders:");
		for (Order order : orders) {
			System.out.println("Order ID: " + order.getOrderId() + ", Customer: " + order.getCustomer().getName()
					+ ", Total: ₹" + order.getTotalAmount() + ", Status: " + order.getStatus());
		}
	}
}
