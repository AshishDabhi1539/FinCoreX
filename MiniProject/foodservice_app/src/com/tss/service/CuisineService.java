package com.tss.service;

import com.tss.model.cuisine.Cuisine;
import com.tss.model.menu.BaseMenuItem;
import com.tss.util.DataStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        return DataStore.readFromFile(path);  // correct method name
    }

    public void saveCuisine(Cuisine cuisine) {
        String path = CUISINE_PATH + cuisine.getName().toLowerCase() + ".ser";
        DataStore.saveToFile(cuisine, path);  // correct method name
    }


    public void createCuisine(Cuisine cuisine) {
        saveCuisine(cuisine);
        System.out.println("✅ New cuisine created: " + cuisine.getName());
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

    public void addMenuItem(Cuisine cuisine, BaseMenuItem item) {
        cuisine.addItem(item);
        saveCuisine(cuisine);
        System.out.println("✅ Item added to " + cuisine.getName() + ": " + item.getName());
    }

    public void removeMenuItem(Cuisine cuisine, String itemId) {
        cuisine.removeItemById(itemId);
        saveCuisine(cuisine);
        System.out.println("✅ Item removed: " + itemId);
    }

    public void printAllCuisines() {
        for (String name : listAvailableCuisines()) {
            Cuisine c = loadCuisine(name);
            if (c != null) c.printMenu();
        }
    }
}
