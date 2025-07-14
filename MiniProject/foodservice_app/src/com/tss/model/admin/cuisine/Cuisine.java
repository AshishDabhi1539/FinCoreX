package com.tss.model.admin.cuisine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tss.model.admin.menu.IFoodItem;

public class Cuisine implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private List<IFoodItem> foodItems = new ArrayList<>();

	public Cuisine(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<IFoodItem> getFoodItems() {
		return foodItems;
	}

	public void addFoodItem(IFoodItem item) {
		foodItems.add(item);
	}

	public void removeFoodItem(String name) {
		foodItems.removeIf(item -> item.getName().equalsIgnoreCase(name));
	}

	public void updateItemPrice(String name, double price) {
		for (IFoodItem item : foodItems) {
			if (item.getName().equalsIgnoreCase(name)) {
				item.setPrice(price);
				break;
			}
		}
	}
}
