package com.tss.model.inheritance.model;

public class Car extends Vehicle {
	
	private int numberOfDoors;

	public Car(String model, int year, int price, int numberOfDoors) {
		super(model, year, price);
		this.numberOfDoors = numberOfDoors;
	}

	public String lockDoors() {
		return "Doors are locking";
	}
	
	public String unlockDoors() {
		return "Doors are unlocking";
	}

	public int getNumberOfDoors() {
		return numberOfDoors;
	}

	public void setNumberOfDoors(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}
	
}
