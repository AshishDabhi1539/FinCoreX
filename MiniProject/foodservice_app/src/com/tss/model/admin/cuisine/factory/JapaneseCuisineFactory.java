package com.tss.model.admin.cuisine.factory;



import com.tss.model.admin.cuisine.Cuisine;
import com.tss.model.admin.menu.SimpleFoodItem;

public class JapaneseCuisineFactory implements CuisineFactory {

    @Override
    public Cuisine createCuisine() {
        Cuisine japanese = new Cuisine("Japanese");
        japanese.addFoodItem(new SimpleFoodItem("Sushi", 300));
        japanese.addFoodItem(new SimpleFoodItem("Ramen", 250));
        return japanese;
    }
}

