package com.tss.model.cuisine.factory;

import com.tss.model.cuisine.Cuisine;
import com.tss.model.menu.BaseMenuItem;

public class KoreanCuisineFactory implements ICuisineFactory {
    @Override
    public Cuisine createCuisine() {
        Cuisine cuisine = new Cuisine("Korean");
        cuisine.addItem(new BaseMenuItem("K301", "Kimchi", "Spicy fermented cabbage", 150));
        cuisine.addItem(new BaseMenuItem("K302", "Bibimbap", "Mixed rice with vegetables", 280));
        return cuisine;
    }
}
