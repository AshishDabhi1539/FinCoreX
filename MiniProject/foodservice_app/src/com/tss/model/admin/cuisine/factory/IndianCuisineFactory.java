package com.tss.model.admin.cuisine.factory;
import com.tss.model.admin.cuisine.Cuisine;
import com.tss.model.admin.menu.SimpleFoodItem;

public class IndianCuisineFactory implements CuisineFactory {

    @Override
    public Cuisine createCuisine() {
        Cuisine indian = new Cuisine("Indian");
        indian.addFoodItem(new SimpleFoodItem("Biryani", 180));
        indian.addFoodItem(new SimpleFoodItem("Paneer Butter Masala", 150));
        indian.addFoodItem(new SimpleFoodItem("Naan", 40));
        return indian;
    }
    
}
