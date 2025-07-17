package com.tss.model.cuisine.factory;

import com.tss.model.cuisine.Cuisine;
import com.tss.model.cuisine.IndianCuisine;

public class IndianCuisineFactory implements CuisineFactory {
    public Cuisine createCuisine() {
        return new IndianCuisine();
    }
}
