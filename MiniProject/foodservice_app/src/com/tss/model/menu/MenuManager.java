package com.tss.model.menu;



import java.util.Scanner;
import java.util.UUID;

import com.tss.model.cuisine.Cuisine;
import com.tss.service.CuisineService;

public class MenuManager {

    private final CuisineService cuisineService = CuisineService.getInstance();

    public void openMenuManager(Scanner scanner) {
        while (true) {
            System.out.println("\nMENU MANAGEMENT");
            System.out.println("1. View All Cuisines & Menu");
            System.out.println("2. Add New Cuisine");
            System.out.println("3. Add Menu Item to Cuisine");
            System.out.println("4. Remove Menu Item from Cuisine");
            System.out.println("5. Alter Item Price / Description");
            System.out.println("6. Exit Admin Menu Manager");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> cuisineService.printAllCuisines();
                case "2" -> addNewCuisine(scanner);
                case "3" -> addMenuItem(scanner);
                case "4" -> removeMenuItem(scanner);
                case "5" -> alterMenuItem(scanner);
                case "6" -> {
                    System.out.println("Exiting Menu Manager.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addNewCuisine(Scanner sc) {
        System.out.print("Enter Cuisine Name (e.g., Italian): ");
        String name = sc.nextLine();
        Cuisine newCuisine = new Cuisine(name);

        System.out.print("How many items to add? ");
        int count = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < count; i++) {
            System.out.println("Item " + (i + 1) + ":");
            BaseMenuItem item = getMenuItemInput(sc, name.charAt(0));
            newCuisine.addItem(item);
        }

        cuisineService.createCuisine(newCuisine);
    }

    private void addMenuItem(Scanner sc) {
        String cuisineName = getCuisineNameFromUser(sc);
        Cuisine cuisine = cuisineService.loadCuisine(cuisineName);
        if (cuisine == null) return;

        BaseMenuItem item = getMenuItemInput(sc, cuisineName.charAt(0));
        cuisineService.addMenuItem(cuisine, item);
    }

    private void removeMenuItem(Scanner sc) {
        String cuisineName = getCuisineNameFromUser(sc);
        Cuisine cuisine = cuisineService.loadCuisine(cuisineName);
        if (cuisine == null) return;

        cuisine.printMenu();
        System.out.print("Enter Item ID to remove: ");
        String id = sc.nextLine();
        cuisineService.removeMenuItem(cuisine, id);
    }

    private void alterMenuItem(Scanner sc) {
        String cuisineName = getCuisineNameFromUser(sc);
        Cuisine cuisine = cuisineService.loadCuisine(cuisineName);
        if (cuisine == null) return;

        cuisine.printMenu();
        System.out.print("Enter Item ID to edit: ");
        String id = sc.nextLine();
        BaseMenuItem item = cuisine.getItemById(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.print("New Price (leave empty to skip): ");
        String priceInput = sc.nextLine();
        if (!priceInput.isEmpty()) {
            item.setPrice(Double.parseDouble(priceInput));
        }

        System.out.print("New Description (leave empty to skip): ");
        String descInput = sc.nextLine();
        if (!descInput.isEmpty()) {
            item.setDescription(descInput);
        }

        cuisineService.saveCuisine(cuisine);
        System.out.println("Item updated.");
    }

    private BaseMenuItem getMenuItemInput(Scanner sc, char prefix) {
        String id = prefix + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Description: ");
        String description = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(sc.nextLine());

        return new BaseMenuItem(id, name, description, price);
    }

    private String getCuisineNameFromUser(Scanner sc) {
        System.out.println("Available Cuisines:");
        cuisineService.listAvailableCuisines().forEach(System.out::println);
        System.out.print("Enter Cuisine Name: ");
        return sc.nextLine();
    }
}
