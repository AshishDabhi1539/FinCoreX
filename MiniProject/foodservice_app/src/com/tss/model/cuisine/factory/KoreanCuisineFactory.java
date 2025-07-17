package com.tss.model.cuisine.factory;

import com.tss.model.cuisine.Cuisine;
import com.tss.model.cuisine.KoreanCuisine;

public class KoreanCuisineFactory implements CuisineFactory {
    public Cuisine createCuisine() {
        return new KoreanCuisine();
    }
}
