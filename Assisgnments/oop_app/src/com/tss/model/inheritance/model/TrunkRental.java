package com.tss.model.inheritance.model;

import com.tss.model.VehicleRental;

public class TrunkRental extends VehicleRental {
	
	
	 public TrunkRental(int vehicleNumber, double rentPerDay, String fuelType) {
		super(vehicleNumber, rentPerDay, fuelType);
		// TODO Auto-generated constructor stub
	}

	 

	    @Override
	    public double calculateRent(int numberOfDays) {
	        return (rentPerDay * numberOfDays) + 500;
	    }

}
