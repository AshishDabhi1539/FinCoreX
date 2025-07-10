package com.tss.model;

public abstract class VehicleRental {

	protected int vehicleNumber;
	protected double rentPerDay;
	protected String fuelType;
	
	public VehicleRental(int vehicleNumber, double rentPerDay, String fuelType) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.rentPerDay = rentPerDay;
		this.fuelType = fuelType;
	}
	
	
	public abstract double calculateRent(int numberOfDays);
	
	public void displayDetails(int numberOfDays) {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Rent Per Day: " + rentPerDay);
        System.out.println("Total Rent for " + numberOfDays + " days: " + calculateRent(numberOfDays));
    }
	
	
	
	
}
