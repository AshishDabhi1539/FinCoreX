package com.tss.model.admin.cuisine.factory;

import com.tss.model.admin.cuisine.Cuisine;
import com.tss.model.admin.menu.SimpleFoodItem;

public class FrenchCuisineFactory implements CuisineFactory {

    @Override
    public Cuisine createCuisine() {
        Cuisine french = new Cuisine("French");
        french.addFoodItem(new SimpleFoodItem("Croissant", 90));
        french.addFoodItem(new SimpleFoodItem("Ratatouille", 200));
        return french;
    }
}
