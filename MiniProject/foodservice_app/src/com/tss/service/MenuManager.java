package com.tss.service;

import com.tss.model.menu.BaseMenuItem;
import com.tss.model.cuisine.Cuisine;
import com.tss.exception.CuisineNotFoundException;

import java.util.List;
import java.util.Scanner;

public class MenuManager {
    private final CuisineService cuisineService = CuisineService.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public void displayCuisines() {
        @SuppressWarnings("static-access")
		List<String> cuisines = cuisineService.listAvailableCuisines();
        System.out.println("📋 Available Cuisines:");
        for (int i = 0; i < cuisines.size(); i++) {
            System.out.println((i + 1) + ". " + cuisines.get(i));
        }
    }

    @SuppressWarnings("static-access")
	public Cuisine selectCuisine() throws CuisineNotFoundException {
        displayCuisines();
        System.out.print("👉 Select Cuisine by Number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        List<String> cuisineNames = cuisineService.listAvailableCuisines();
        if (choice > 0 && choice <= cuisineNames.size()) {
        	return cuisineService.loadCuisine(cuisineNames.get(choice - 1));

        } else {
            System.out.println("❌ Invalid choice.");
        }
        return null;
    }

    public void showMenuForCustomer(Cuisine cuisine) {
        if (cuisine == null) {
            System.out.println("❌ Invalid cuisine selected.");
            return;
        }

        List<BaseMenuItem> items = cuisine.getMenuItems();
        if (items.isEmpty()) {
            System.out.println("❌ No items available in this cuisine.");
        } else {
            System.out.println("\n🍴 " + cuisine.getName() + " Menu:");
            for (int i = 0; i < items.size(); i++) {
                BaseMenuItem item = items.get(i);
                System.out.printf("%d. %s - ₹%.2f\n   %s\n", i + 1, item.getName(), item.getPrice(), item.getDescription());
            }
        }
    }

    public static List<BaseMenuItem> getMenuItems(Cuisine cuisine) {
        return cuisine != null ? cuisine.getMenuItems() : null;
    }
}
