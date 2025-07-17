package com.tss.model.cuisine.factory;

public class CuisineFactoryProvider {
    public static CuisineFactory getFactory(String cuisineType) {
        switch (cuisineType.toLowerCase()) {
            case "indian":
                return new IndianCuisineFactory();
            case "japanese":
                return new JapaneseCuisineFactory();
            case "korean":
                return new KoreanCuisineFactory();
            case "french":
                return new FrenchCuisineFactory();
            default:
                throw new IllegalArgumentException("❌ Unsupported cuisine: " + cuisineType);
        }
    }
}