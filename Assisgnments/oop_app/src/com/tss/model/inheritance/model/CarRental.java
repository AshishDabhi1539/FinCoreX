package com.tss.model.inheritance.model;

import com.tss.model.VehicleRental;

public class CarRental extends VehicleRental{

	public CarRental(int carNumber, double rentPerDay, String fuelType) {
		super(carNumber, rentPerDay, fuelType);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
    public double calculateRent(int numberOfDays) {
        return rentPerDay * numberOfDays;
    }

}
