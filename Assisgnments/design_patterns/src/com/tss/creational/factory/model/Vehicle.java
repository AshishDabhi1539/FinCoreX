package com.tss.creational.factory.model;

public class Vehicle {

	private static Vehicle vehicle = null;
	
	private Vehicle() {
		
	}
	
	public static Vehicle getInstance() {
        if (vehicle == null) {
            vehicle = new Vehicle();
        }
        return vehicle;
    }
	
	public static ILicenseVehicle getVehicle(String type) {
        if (type == null) return null;

        switch (type.toLowerCase()) {
            case "twowheeler":
                return new TwoWheeler();
            case "fourwheeler":
                return new FourVehicle();
            case "heavyvehicle":
                return new HeavyVehicle();
            default:
                return null;
        }
}
}