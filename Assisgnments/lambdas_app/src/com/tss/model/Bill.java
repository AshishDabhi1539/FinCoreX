package com.tss.model;

public class Bill {

	private String name;
    private double price;
	public Bill(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	

}
