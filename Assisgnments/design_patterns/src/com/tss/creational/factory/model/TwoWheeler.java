package com.tss.creational.factory.model;

public class TwoWheeler implements ILicenseVehicle{

	@Override
	public String generateLicensePlat() {
		// TODO Auto-generated method stub
	        return "TW" + ((int)(Math.random() * 9000) + 1000);
	}

}
