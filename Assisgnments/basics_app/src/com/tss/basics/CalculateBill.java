package com.tss.basics;

import java.util.Scanner;

public class CalculateBill {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		float units = scanner.nextFloat();
		
		int Meter_Charge = 75;
		
		calculateTheBill(units, Meter_Charge);
	}
	
	private static void calculateTheBill(float units, int Meter_Charge) {
		if (units<=100) {
			float charge = units * 5;
			float Bill = charge + Meter_Charge;
			System.out.println("Meter Charge: " + Bill);
			
		}
		
		else {
			if (units<=250) {
				float charge = units * 10;
				float Bill = charge + Meter_Charge;
				System.out.println("Meter Charge: " + Bill);
			}
			
			else {
				float charge = units * 20;
				float Bill = charge + Meter_Charge;
				System.out.println("Meter Charge: " + Bill);
			}
		}
	}

}
