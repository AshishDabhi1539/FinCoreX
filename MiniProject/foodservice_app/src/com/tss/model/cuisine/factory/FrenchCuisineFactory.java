package com.tss.model.cuisine.factory;

import com.tss.model.cuisine.Cuisine;
import com.tss.model.menu.BaseMenuItem;

public class FrenchCuisineFactory implements ICuisineFactory {
    @Override
    public Cuisine createCuisine() {
        Cuisine cuisine = new Cuisine("French");
        cuisine.addItem(new BaseMenuItem("F401", "Croissant", "Flaky buttery pastry", 80));
        cuisine.addItem(new BaseMenuItem("F402", "Ratatouille", "Vegetable stew", 200));
        return cuisine;
    }
}
