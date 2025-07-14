package com.tss.model.admin.menu;

public class SimpleFoodItem implements IFoodItem {
   
	private static final long serialVersionUID = 1L;
	private final String name;
    private double price;

    public SimpleFoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - ₹" + price;
    }
}
