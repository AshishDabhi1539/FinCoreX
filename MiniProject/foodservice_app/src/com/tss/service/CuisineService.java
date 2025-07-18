package com.tss.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.tss.exception.CuisineNotFoundException;
import com.tss.model.cuisine.Cuisine;
import com.tss.model.menu.BaseMenuItem;
import com.tss.util.DataStore;

public class CuisineService {
    private static CuisineService instance;
    private static final String CUISINE_PATH = "data/cuisines/";

    private CuisineService() {}

    public static CuisineService getInstance() {
        if (instance == null) {
            instance = new CuisineService();
        }
        return instance;
    }

    public Cuisine loadCuisine(String cuisineName) {
        String path = CUISINE_PATH + cuisineName.toLowerCase() + ".ser";
        return DataStore.readFromFile(path);
    }

    public Cuisine loadCuisineOrThrow(String cuisineName) throws CuisineNotFoundException {
        Cuisine cuisine = loadCuisine(cuisineName);
        if (cuisine == null) {
            throw new CuisineNotFoundException("Cuisine not found: " + cuisineName);
        }
        return cuisine;
    }

    public void saveCuisine(Cuisine cuisine) {
        String path = CUISINE_PATH + cuisine.getName().toLowerCase() + ".ser";
        DataStore.saveToFile(cuisine, path);
    }

    public void createCuisine(Cuisine cuisine) {
        saveCuisine(cuisine);
        System.out.println("New cuisine created: " + cuisine.getName());
    }

    public static List<String> listAvailableCuisines() {
        File dir = new File(CUISINE_PATH);
        List<String> cuisines = new ArrayList<>();
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith(".ser")) {
                    String cuisineName = file.getName().replace(".ser", "");
                    cuisines.add(cuisineName.substring(0, 1).toUpperCase() + cuisineName.substring(1));
                }
            }
        }
        return cuisines;
    }

    public List<String> getAllCuisineNames() {
        List<String> cuisineNames = new ArrayList<>();
        File folder = new File(CUISINE_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                String cuisineName = fileName.substring(0, fileName.lastIndexOf('.')); 
                cuisineNames.add(cuisineName);
            }
        }

        return cuisineNames;
    }

    public void printAllCuisineNames() {
        List<String> cuisines = getAllCuisineNames();
        if (cuisines.isEmpty()) {
            System.out.println("No cuisines found.");
        } else {
            System.out.println("\nAvailable Cuisines:");
            cuisines.forEach(name -> System.out.println("🔹 " + name));
            System.out.println();
        }
    }

    public void addMenuItem(Cuisine cuisine, BaseMenuItem item) {
        cuisine.addItem(item);
        saveCuisine(cuisine);
        System.out.println("Item added to " + cuisine.getName() + ": " + item.getName());
    }

    public void removeMenuItem(Cuisine cuisine, String itemId) {
        cuisine.removeItemById(itemId);
        saveCuisine(cuisine);
        System.out.println("Item removed: " + itemId);
    }

    public void printAllCuisines() {
        for (String name : listAvailableCuisines()) {
            Cuisine c = loadCuisine(name);
            if (c != null) c.printMenu();
        }
    }
}
