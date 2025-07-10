package com.tss.model.inheritance.model;

import com.tss.model.VehicleRental;

public class BikeRental extends VehicleRental {

	public BikeRental(int vehicleNumber, double rentPerDay, String fuelType) {
		super(vehicleNumber, rentPerDay, fuelType);
		// TODO Auto-generated constructor stub
	}

	@Override
    public double calculateRent(int numberOfDays) {
        double total = rentPerDay * numberOfDays;
        if (numberOfDays > 5) {
            total *= 0.90; // 10% discount
        }
        return total;
    }
	
}
