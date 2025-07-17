package com.tss.admin;

import java.util.Scanner;
import java.util.UUID;

import com.tss.discounts.DiscountManager;
import com.tss.model.cuisine.Cuisine;
import com.tss.model.cuisine.factory.CuisineFactory;
import com.tss.model.cuisine.factory.FrenchCuisineFactory;
import com.tss.model.cuisine.factory.IndianCuisineFactory;
import com.tss.model.cuisine.factory.JapaneseCuisineFactory;
import com.tss.model.cuisine.factory.KoreanCuisineFactory;
import com.tss.model.menu.BaseMenuItem;
import com.tss.service.CuisineService;

public class AdminPanel {
    private final Scanner scanner;
    private final CuisineService cuisineService = CuisineService.getInstance();

    
    
    public AdminPanel(Scanner scanner) {
        this.scanner = scanner;
    }
    
    

	

    public void showAdminMenu() {
        while (true) {
            System.out.println("\n========= 🛠️ Admin Panel =========");
            System.out.println("1. Manage Menu");
            System.out.println("2. Manage Discounts");
            System.out.println("3. View All Cuisines");
            System.out.println("4. Back to Main Menu");
            System.out.print("👉 Enter your choice: ");

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
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private void manageMenu() {
        while (true) {
            System.out.println("\n========= 🍽️ Manage Menu =========");
            System.out.println("1. Create New Cuisine");
            System.out.println("2. Add Menu Item to Cuisine");
            System.out.println("3. Remove Menu Item from Cuisine");
            System.out.println("4. Back");
            System.out.print("👉 Enter your choice: ");

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
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private void createCuisine() {
        System.out.print("🍳 Enter Cuisine Name (Indian/Korean/Japanese/French): ");
        String name = scanner.nextLine().trim().toLowerCase();
        CuisineFactory factory = null;

        switch (name) {
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
                System.out.println("❌ Invalid cuisine type.");
                return;
        }

        Cuisine cuisine = factory.createCuisine();
        cuisineService.createCuisine(cuisine);
    }

    private void addMenuItem() {
        System.out.print("🍽️ Enter Cuisine Name: ");
        String cuisineName = scanner.nextLine().trim();
        Cuisine cuisine = cuisineService.loadCuisine(cuisineName);

        if (cuisine == null) {
            System.out.println("❌ Cuisine not found.");
            return;
        }

        System.out.print("📝 Enter Item Name: ");
        String name = scanner.nextLine();
        System.out.print("💬 Enter Description: ");
        String desc = scanner.nextLine();
        System.out.print("💰 Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("📂 Enter Category (VEG/NON_VEG/BEVERAGE/DESSERT): ");
        String cat = scanner.nextLine().toUpperCase();

//        MenuCategory category;
//        try {
//            category = MenuCategory.valueOf(cat);
//        } catch (IllegalArgumentException e) {
//            System.out.println("❌ Invalid category.");
//            return;
//        }

        String id = UUID.randomUUID().toString(); // or use a helper method
        BaseMenuItem item = new BaseMenuItem(id, name, desc, price);
        cuisineService.addMenuItem(cuisine, item);
       
    }

    private void removeMenuItem() {
        System.out.print("🍽️ Enter Cuisine Name: ");
        String cuisineName = scanner.nextLine().trim();
        Cuisine cuisine = cuisineService.loadCuisine(cuisineName);

        if (cuisine == null) {
            System.out.println("❌ Cuisine not found.");
            return;
        }

        cuisine.printMenu();
        System.out.print("🗑️ Enter Item ID to Remove: ");
        String id = scanner.nextLine();
        cuisineService.removeMenuItem(cuisine, id);
    }
}
