package com.tss.admin;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.tss.discounts.DiscountManager;
import com.tss.model.cuisine.Cuisine;
import com.tss.model.cuisine.factory.CuisineFactory;
import com.tss.model.cuisine.factory.FrenchCuisineFactory;
import com.tss.model.cuisine.factory.IndianCuisineFactory;
import com.tss.model.cuisine.factory.JapaneseCuisineFactory;
import com.tss.model.cuisine.factory.KoreanCuisineFactory;
import com.tss.model.menu.BaseMenuItem;
import com.tss.service.CuisineService;
import com.tss.util.IdGenerator;

public class AdminPanel {
    private final Scanner scanner;
    private final CuisineService cuisineService = CuisineService.getInstance();

    public AdminPanel(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showAdminMenu() {
        while (true) {
            System.out.println("\n========= Admin Panel =========");
            System.out.println("1. Manage Menu");
            System.out.println("2. Manage Discounts");
            System.out.println("3. View All Cuisines");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    manageMenu();
                    break;
                case 2:
                    DiscountManager.manageDiscounts(scanner);
                    break;
                case 3:
                    cuisineService.printAllCuisines();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void manageMenu() {
        while (true) {
            System.out.println("\n========= Manage Menu =========");
            System.out.println("1. Create New Cuisine");
            System.out.println("2. Add Menu Item to Cuisine");
            System.out.println("3. Remove Menu Item from Cuisine");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    createCuisine();
                    break;
                case 2:
                    addMenuItem();
                    break;
                case 3:
                    removeMenuItem();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    
    private void createCuisine() {
        List<String> existingCuisines = cuisineService.getAllCuisineNames();

       
        if (!existingCuisines.isEmpty()) {
            System.out.println("Available Cuisines: " +
                existingCuisines.stream()
                                .map(c -> capitalize(c))
                                .collect(Collectors.joining(", ")));
        } else {
            System.out.println("No cuisines created yet.");
        }

        System.out.print("Enter Cuisine Name (Indian/Korean/Japanese/French): ");
        String input = scanner.nextLine().trim().toLowerCase();

        // Prevent duplication
        for (String existing : existingCuisines) {
            if (existing.equalsIgnoreCase(input)) {
                System.out.println("Cuisine already exists: " + capitalize(existing));
                return;
            }
        }

        CuisineFactory factory = null;
        switch (input) {
            case "indian":
                factory = new IndianCuisineFactory();
                break;
            case "korean":
                factory = new KoreanCuisineFactory();
                break;
            case "japanese":
                factory = new JapaneseCuisineFactory();
                break;
            case "french":
                factory = new FrenchCuisineFactory();
                break;
            default:
                System.out.println("Invalid cuisine type.");
                return;
        }

        Cuisine cuisine = factory.createCuisine();
        cuisineService.createCuisine(cuisine);
        System.out.println("New cuisine created: " + cuisine.getName());
    }

    private void addMenuItem() {
    	List<String> existingCuisines = cuisineService.getAllCuisineNames();
    	System.out.println("Available Cuisines: " +
                existingCuisines.stream()
                                .map(c -> capitalize(c))
                                .collect(Collectors.joining(", ")));
        System.out.print("Enter Cuisine Name: ");
        String cuisineName = scanner.nextLine().trim();
        Cuisine cuisine = cuisineService.loadCuisine(cuisineName);

        if (cuisine == null) {
            System.out.println("Cuisine not found.");
            return;
        }

        cuisine.printMenu();
        
        System.out.print("Enter Item Name: ");
        String name = scanner.nextLine().trim();

     
        for (BaseMenuItem item : cuisine.getMenuItems()) {
            if (item.getName().equalsIgnoreCase(name)) {
                System.out.println("Item with name '" + name + "' already exists with ID: " + item.getId());
                System.out.print("Do you want to edit it? (yes/no): ");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (choice.equals("yes")) {
                    System.out.print("Enter New Price: ");
                    double price = Double.parseDouble(scanner.nextLine());

                    System.out.print("Enter New Description: ");
                    String desc = scanner.nextLine();

                    item.setPrice(price);
                    item.setDescription(desc);

                    cuisineService.saveCuisine(cuisine); 
                    System.out.println("Item updated: " + item.getId());
                    return;
                } else {
                    System.out.println("Skipping item addition.");
                    return;
                }
            }
        }

        
        System.out.print("Enter Description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());
       
        String itemId = IdGenerator.generate(cuisine.getName());
        BaseMenuItem newItem = new BaseMenuItem(itemId, name, price, desc);

        cuisineService.addMenuItem(cuisine, newItem);
    }


    private void removeMenuItem() {
        cuisineService.printAllCuisineNames(); 
        System.out.print("Enter Cuisine Name: ");
        String cuisineName = scanner.nextLine().trim();
        Cuisine cuisine = cuisineService.loadCuisine(cuisineName);

        if (cuisine == null) {
            System.out.println("Cuisine not found.");
            return;
        }

        cuisine.printMenu(); 
        System.out.print("Enter Item ID to Remove: ");
        String id = scanner.nextLine();
        cuisineService.removeMenuItem(cuisine, id);
    }

    }
