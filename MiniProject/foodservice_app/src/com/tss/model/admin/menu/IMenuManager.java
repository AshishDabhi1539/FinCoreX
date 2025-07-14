package com.tss.model.admin.menu;

public interface IMenuManager {
    void addFoodItem(String cuisineName, String foodName, double price);
    void removeFoodItem(String cuisineName, String foodName);
    void updateFoodItemPrice(String cuisineName, String foodName, double newPrice);
    void applyDiscount(String cuisineName, String foodName, double discountPercentage);
}

