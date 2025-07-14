package com.tss.model.admin.cuisine;

import java.util.List;

import com.tss.model.admin.cuisine.factory.CuisineFactory;
import com.tss.model.admin.cuisine.factory.FrenchCuisineFactory;
import com.tss.model.admin.cuisine.factory.IndianCuisineFactory;
import com.tss.model.admin.cuisine.factory.JapaneseCuisineFactory;
import com.tss.model.admin.cuisine.factory.KoreanCuisineFactory;

public class CuisineService implements ICuisineManager {
	private static CuisineService instance;
	private final String FILE = "cuisines.ser";
	private List<Cuisine> cuisines;

	private CuisineService() {
		cuisines = DataStore.loadFromFile(FILE);
	}

	public static CuisineService getInstance() {
		if (instance == null)
			instance = new CuisineService();
		return instance;
	}

	@Override
	public void addCuisine(Cuisine cuisine) {
		cuisines.add(cuisine);
	}

	@Override
	public void removeCuisine(String name) {
		cuisines.removeIf(c -> c.getName().equalsIgnoreCase(name));
	}

	@Override
	public Cuisine getCuisine(String name) {
		return cuisines.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}

	@Override
	public List<Cuisine> getAllCuisines() {
		return cuisines;
	}

	public void saveCuisines() {
		DataStore.saveToFile(cuisines, FILE);
	}
	
	private void loadDefaultCuisines() {
	    CuisineFactory indianFactory = new IndianCuisineFactory();
	    CuisineFactory japaneseFactory = (CuisineFactory) new JapaneseCuisineFactory();
	    CuisineFactory koreanFactory = (CuisineFactory) new KoreanCuisineFactory();
	    CuisineFactory frenchFactory = (CuisineFactory) new FrenchCuisineFactory();

	    cuisines.add(indianFactory.createCuisine());
	    cuisines.add(japaneseFactory.createCuisine());
	    cuisines.add(koreanFactory.createCuisine());
	    cuisines.add(frenchFactory.createCuisine());
	}

}
