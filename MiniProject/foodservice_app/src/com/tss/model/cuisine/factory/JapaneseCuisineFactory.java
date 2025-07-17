package com.tss.model.cuisine.factory;

import com.tss.model.cuisine.Cuisine;
import com.tss.model.cuisine.JapaneseCuisine;

public class JapaneseCuisineFactory implements CuisineFactory {
    public Cuisine createCuisine() {
        return new JapaneseCuisine();
    }
}
