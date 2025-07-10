package com.tss.creational.factory.model;

public class FourVehicle implements ILicenseVehicle{

	@Override
	public String generateLicensePlat() {
		// TODO Auto-generated method stub
		 return "FW" + ((int)(Math.random() * 9000) + 1000);
	}

}
