package com.tss.model;

public class ElectricityBill {

	private int appartmentNumber;
	private double unitsConsumed;
	private static double costPerUnit;
	
	public ElectricityBill() {
		int appartmentNumber = 12;
		double unitsConsumed = 200;
	}
	
	public ElectricityBill(int appartmentNumber, double unitsConsumed) {
		this.appartmentNumber = appartmentNumber;
		this.unitsConsumed = unitsConsumed;
	}
	
	
	public int getAppartmentNumber() {
		return appartmentNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.appartmentNumber = accountNumber;
	}
	public double getUnitsConsumed() {
		return unitsConsumed;
	}
	public void setUnitsConsumed(double unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}
	public static double getCostPerUnit() {
		return costPerUnit;
	}
	public static void setCostPerUnit(double costPerUnit) {
		ElectricityBill.costPerUnit = costPerUnit;
	}
	
	public double calculateBillOfUnits() {
		return  this.getUnitsConsumed() * ElectricityBill.getCostPerUnit();
		
		
	}
	
	public void display() {
		System.out.println("Appartment Number:"+appartmentNumber);
		System.out.println("Units Consumed are:"+unitsConsumed);
		System.out.println("Bill Generated is:"+calculateBillOfUnits());
	}
	
}
