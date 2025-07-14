package com.tss.model.admin.menu;

import com.tss.model.admin.cuisine.Cuisine;
import com.tss.model.admin.cuisine.CuisineService;
import com.tss.model.admin.cuisine.ICuisineManager;

public class MenuManager implements IMenuManager {

    private final ICuisineManager cuisineService = CuisineService.getInstance();

    @Override
    public void addFoodItem(String cuisineName, String foodName, double price) {
        Cuisine cuisine = cuisineService.getCuisine(cuisineName);
        if (cuisine == null) {
            System.out.println("Cuisine not found.");
            return;
        }

        cuisine.addFoodItem(new SimpleFoodItem(foodName, price));
        System.out.println("Food item added.");
    }

    @Override
    public void removeFoodItem(String cuisineName, String foodName) {
        Cuisine cuisine = cuisineService.getCuisine(cuisineName);
        if (cuisine == null) {
            System.out.println("Cuisine not found.");
            return;
        }

        cuisine.removeFoodItem(foodName);
        System.out.println("Food item removed.");
    }

    @Override
    public void updateFoodItemPrice(String cuisineName, String foodName, double newPrice) {
        Cuisine cuisine = cuisineService.getCuisine(cuisineName);
        if (cuisine == null) {
            System.out.println("Cuisine not found.");
            return;
        }

        cuisine.updateItemPrice(foodName, newPrice);
        System.out.println("Food item price updated.");
    }

    @Override
    public void applyDiscount(String cuisineName, String foodName, double discountPercentage) {
        Cuisine cuisine = cuisineService.getCuisine(cuisineName);
        if (cuisine == null) {
            System.out.println("Cuisine not found.");
            return;
        }

        for (IFoodItem item : cuisine.getFoodItems()) {
            if (item.getName().equalsIgnoreCase(foodName)) {
                double oldPrice = item.getPrice();
                double newPrice = oldPrice - (oldPrice * (discountPercentage / 100));
                item.setPrice(newPrice);
                System.out.println("Discount applied. New Price: ₹" + newPrice);
                return;
            }
        }

        System.out.println("Food item not found.");
    }
}
