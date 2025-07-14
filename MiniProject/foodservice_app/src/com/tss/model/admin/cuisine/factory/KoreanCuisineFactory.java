package com.tss.model.admin.cuisine.factory;

import com.tss.model.admin.cuisine.Cuisine;
import com.tss.model.admin.menu.SimpleFoodItem;

public class KoreanCuisineFactory implements CuisineFactory {

    @Override
    public Cuisine createCuisine() {
        Cuisine korean = new Cuisine("Korean");
        korean.addFoodItem(new SimpleFoodItem("Kimchi", 120));
        korean.addFoodItem(new SimpleFoodItem("Bibimbap", 260));
        return korean;
    }
}
