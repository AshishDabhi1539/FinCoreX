package com.tss.service;

import com.tss.model.cuisine.Cuisine;
import com.tss.model.menu.BaseMenuItem;
import com.tss.util.DataStore;

import java.util.*;

public class CuisineService {
    private static CuisineService instance;
    private static final String CUISINE_PATH = "data/cuisines/"; // Folder to store cuisine files

    private CuisineService() {}

    public static CuisineService getInstance() {
        if (instance == null) instance = new CuisineService();
        return instance;
    }

    public Cuisine loadCuisine(String cuisineName) {
        String path = CUISINE_PATH + cuisineName.toLowerCase() + ".ser";
        Cuisine cuisine = (Cuisine) DataStore.readObject(path);
        if (cuisine == null) {
            System.out.println("⚠️ Cuisine not found or empty: " + cuisineName);
        }
        return cuisine;
    }

    public void saveCuisine(Cuisine cuisine) {
        String path = CUISINE_PATH + cuisine.getName().toLowerCase() + ".ser";
        DataStore.writeObject(path, cuisine);
    }

    public List<String> listAvailableCuisines() {
        return List.of("Indian", "Japanese", "Korean", "French"); // Add dynamic scan later if needed
    }

    public void addMenuItem(Cuisine cuisine, BaseMenuItem item) {
        cuisine.addItem(item);
        saveCuisine(cuisine);
        System.out.println("✅ Item added: " + item.getName());
    }

    public void removeMenuItem(Cuisine cuisine, String itemId) {
        cuisine.removeItemById(itemId);
        saveCuisine(cuisine);
        System.out.println("✅ Item removed: " + itemId);
    }

    public void createCuisine(Cuisine cuisine) {
        saveCuisine(cuisine);
        System.out.println("✅ New cuisine added: " + cuisine.getName());
    }

    public void printAllCuisines() {
        for (String cuisineName : listAvailableCuisines()) {
            Cuisine cuisine = loadCuisine(cuisineName);
            if (cuisine != null) cuisine.printMenu();
        }
    }
}
