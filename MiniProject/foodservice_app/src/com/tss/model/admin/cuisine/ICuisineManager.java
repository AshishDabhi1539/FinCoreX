package com.tss.model.admin.cuisine;

import java.util.List;

public interface ICuisineManager {

	void addCuisine(Cuisine cuisine);
    void removeCuisine(String name);
    Cuisine getCuisine(String name);
    List<Cuisine> getAllCuisines();
}
