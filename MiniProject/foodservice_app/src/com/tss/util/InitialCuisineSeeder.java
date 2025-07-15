package com.tss.util;

import com.tss.model.cuisine.Cuisine;
import com.tss.model.cuisine.factory.FrenchCuisineFactory;
import com.tss.model.cuisine.factory.ICuisineFactory;
import com.tss.model.cuisine.factory.IndianCuisineFactory;
import com.tss.model.cuisine.factory.JapaneseCuisineFactory;
import com.tss.model.cuisine.factory.KoreanCuisineFactory;
import com.tss.service.CuisineService;

public class InitialCuisineSeeder {
    public static void seedAllCuisines() {
        CuisineService service = CuisineService.getInstance();

        ICuisineFactory[] factories = {
            new IndianCuisineFactory(),
            new KoreanCuisineFactory(),
            new JapaneseCuisineFactory(),
            new FrenchCuisineFactory()
        };

        for (ICuisineFactory factory : factories) {
            Cuisine cuisine = factory.createCuisine();
            service.createCuisine(cuisine); // This will serialize it
        }

        System.out.println("✅ Preloaded cuisines with default items.");
    }
}
