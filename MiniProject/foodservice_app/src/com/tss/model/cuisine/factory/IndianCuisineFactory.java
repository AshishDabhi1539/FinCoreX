package com.tss.model.cuisine.factory;

import com.tss.model.cuisine.Cuisine;
import com.tss.model.menu.BaseMenuItem;

public class IndianCuisineFactory implements ICuisineFactory {
    @Override
    public Cuisine createCuisine() {
        Cuisine indian = new Cuisine("Indian");

        indian.addItem(new BaseMenuItem("I101", "Butter Chicken", "Creamy spiced chicken curry", 250));
        indian.addItem(new BaseMenuItem("I102", "Naan", "Tandoori-baked Indian bread", 40));
        indian.addItem(new BaseMenuItem("I103", "Paneer Butter Masala", "Soft paneer in buttery tomato gravy", 220));

        return indian;
    }
}
