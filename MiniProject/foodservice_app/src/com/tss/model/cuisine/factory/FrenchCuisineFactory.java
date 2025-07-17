package com.tss.model.cuisine.factory;

import com.tss.model.cuisine.Cuisine;
import com.tss.model.cuisine.FrenchCuisine;

public class FrenchCuisineFactory implements CuisineFactory {
    public Cuisine createCuisine() {
        return new FrenchCuisine();
    }
}
