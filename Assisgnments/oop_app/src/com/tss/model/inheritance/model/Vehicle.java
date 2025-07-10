package com.tss.model.inheritance.model;

public class Vehicle {

	private String model;
	private int year;
	private int price;
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	public Vehicle(String model, int year, int price) {
		this.model = model;
		this.year = year;
		this.price = price;
	}
	
	public String startEngine() {
		return "Start Engine";
	}
	
	public String stopEngine() {
		return "Stop Engine";
	}
	
	public void display() {
		System.out.println("Model of the vehicle is: "+model);
		System.out.println("Year of the vehicle is: "+year);
		System.out.println("Price of the vehicle is: "+price);
	}
	
}
