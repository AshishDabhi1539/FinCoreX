package com.tss.model.cuisine.factory;

import com.tss.model.cuisine.Cuisine;
import com.tss.model.menu.BaseMenuItem;

public class JapaneseCuisineFactory implements ICuisineFactory {
    @Override
    public Cuisine createCuisine() {
        Cuisine cuisine = new Cuisine("Japanese");
        cuisine.addItem(new BaseMenuItem("J201", "Sushi", "Rice with fish/veggies", 350));
        cuisine.addItem(new BaseMenuItem("J202", "Ramen", "Japanese noodle soup", 300));
        return cuisine;
    }
}
