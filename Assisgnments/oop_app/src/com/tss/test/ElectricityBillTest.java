package com.tss.test;

import java.util.Scanner;

import com.tss.model.ElectricityBill;

public class ElectricityBillTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("PLease enter the cost per unit: ");
		double costPerUnit = scanner.nextDouble();
		
		System.out.println("ENter the number of appartments: ");
		int numberOfAppartments = scanner.nextInt();
		
		ElectricityBill appartments[] = new ElectricityBill[numberOfAppartments];
		
		int i =0;
		ElectricityBill appartment = null;
		
		boolean loop = true;
		
		while(loop) {
			if( i< numberOfAppartments) {
				System.out.println("Please select the service");
				}
				System.out.println("1. Enter appartment details");
				System.out.println("2. Government fixing the price of the units.");
				System.out.println("3. Display appartments.");
				System.out.println("4. Display current Rate.");
				System.out.println("5. Exit");
				System.out.print("Enter your choice: ");
				int choice = scanner.nextInt();
				
				if(i>=numberOfAppartments) {
					if(choice == 1) {
						System.out.println("Invalid Option!");
						continue;
					}
				}
				
				switch(choice) {
				case 1 : 
					getInfoOfAppartments(scanner, appartment, i);
					i++;
					break;
					
				case 2: 
					
					System.out.println("Enter the appartment number");
					int appartmentNumber = scanner.nextInt();
					ElectricityBill electricity = findAppartmentId(appartments, appartmentNumber);

					if (electricity != null) {
						electricity.display();
					} else {
						System.out.println("Appartment is Not Registered");
					}
					break;
					
				case 3:
					System.out.println("Enter New Amount That you want to use as cost per unit: ");
					costPerUnit = scanner.nextDouble();

					ElectricityBill.setCostPerUnit(costPerUnit);
					break;
					
				case 4:
					System.out.println("Current Rate Per Unit is: " + ElectricityBill.getCostPerUnit());
					break;
					
				case 5:
					System.out.println("Exiting. Thank you!");
					loop = false;
					break;

				}
				
				
				
				
		}
	}

	private static ElectricityBill findAppartmentId(ElectricityBill[] appartments, int appartmentNumber) {
		// TODO Auto-generated method stub
		for (int i = 0; i < appartments.length; i++) {
			if (appartments[i] != null && appartments[i].getAppartmentNumber() == appartmentNumber) {
				return appartments[i];
			}
		}
		return null;
	}

	private static void getInfoOfAppartments(Scanner scanner, ElectricityBill appartment, int i) {
		// TODO Auto-generated method stub
		int appartmentNumber = i + 1;
		System.out.println("PLease enter the apartment number: ");
		appartmentNumber = scanner.nextInt();
		
		scanner.nextLine();
		System.out.println("Please enter the units if the apartment: ");
		double unitsConsumed = scanner.nextDouble();
		
		appartment = new ElectricityBill( appartmentNumber, unitsConsumed);
		
		
	}

}
