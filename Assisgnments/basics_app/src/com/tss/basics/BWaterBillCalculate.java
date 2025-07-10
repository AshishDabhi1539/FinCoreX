package com.tss.basics;

import java.util.Scanner;

public class BWaterBillCalculate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the units:");
		float units = scanner.nextFloat();
		
		int meter_charge = 75;
		
		calculateTheBill(units, meter_charge);
	}
	
	private static void calculateTheBill(float units, int meter_charge) {
		float bill;
		if (units<=100) {
			bill = (units * 5);
		}
		
		else {
			if(units<=250) {
			bill = 500 + ((units -100)*10);
			}
			
			else {
			bill = 1500 + ((units - 250)*20);
			}
		}
		
		float finalbill = bill + meter_charge;
		System.out.println("Water Bill: " + finalbill);
	}
}
