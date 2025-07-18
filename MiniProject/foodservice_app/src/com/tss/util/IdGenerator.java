package com.tss.util;

import com.tss.model.menu.BaseMenuItem;
import com.tss.model.cuisine.Cuisine;
import com.tss.service.CuisineService;

import java.util.List;

public class IdGenerator {

    public static String generate(String cuisineName) {
        CuisineService cuisineService = CuisineService.getInstance();
        Cuisine cuisine = cuisineService.loadCuisine(cuisineName);

        if (cuisine == null) {
            return cuisineName.substring(0, 2).toUpperCase() + "001";
        }

        List<BaseMenuItem> items = cuisine.getMenuItems();

        int max = 0;
        for (BaseMenuItem item : items) {
            String id = item.getId(); 
            if (id.length() >= 5) {
                try {
                    String numPart = id.substring(2);
                    int num = Integer.parseInt(numPart);
                    if (num > max) max = num;
                } catch (NumberFormatException ignored) {}
            }
        }

        int nextId = max + 1;
        return cuisineName.substring(0, 2).toUpperCase() + String.format("%03d", nextId);
    }
}
