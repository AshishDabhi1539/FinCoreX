package com.tss.model.inheritance.test;

import java.util.Scanner;

import com.tss.model.VehicleRental;
import com.tss.model.inheritance.model.BikeRental;
import com.tss.model.inheritance.model.CarRental;
import com.tss.model.inheritance.model.TrunkRental;

public class VehicleRentalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		VehicleRental vehicle = null;
		
		int bikeRentPerDay = 500;
		int carRentPerDay = 1000;
		int truckRentPerDay = 1500;
		
		System.out.println("Enter the vehicle: ");
		System.out.println("1. Car");
		System.out.println("2. Bike");
		System.out.println("3. Trunk");
		
		System.out.println("Enter the choice: ");
		int choice = scanner.nextInt();
		
		int numberOfDays;
		
		switch(choice) {
		case 1: 
			System.out.println("Enter Your Car Number: ");
			int carNumber = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter Your Car Fuel Type: ");
			String fuelType = scanner.nextLine();
			
			vehicle = new CarRental(carNumber,carRentPerDay,fuelType);
			
			System.out.println("Enter Number of Days: ");
			numberOfDays = scanner.nextInt();
	
			vehicle.displayDetails(numberOfDays);
			
			break;
		case 2:
			System.out.println("Enter Your Bike Number: ");
			int bikeNumber = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter Your Bike Fuel Type: ");
			String bikeFuelType = scanner.nextLine();
			
			vehicle = new BikeRental(bikeNumber,bikeRentPerDay,bikeFuelType);
			
			System.out.println("Enter Number of Days: ");
			numberOfDays = scanner.nextInt();
			
			
			vehicle.displayDetails(numberOfDays);
			
			break;
			
		case 3:
			System.out.println("Enter Your Truck Number: ");
			int truckNumber = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter Your Truck Fuel Type: ");
			String truckFuelType = scanner.nextLine();
			
			vehicle = new TrunkRental(truckNumber,truckRentPerDay,truckFuelType);
			
			System.out.println("Enter Number of Days: ");
			numberOfDays = scanner.nextInt();
			
			vehicle.displayDetails(numberOfDays);
			
			break;
			
		case 4:
			System.out.println("Exiting");
			break;
		
		default:
			System.out.println("Invalid");
		}
		}
		
		
		
		
		



	
	
}
