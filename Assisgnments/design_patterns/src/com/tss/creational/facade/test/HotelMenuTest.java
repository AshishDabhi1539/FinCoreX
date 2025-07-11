package com.tss.creational.facade.test;

import java.util.Scanner;

import com.tss.creational.facade.model.HotelReception;

public class HotelMenuTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HotelReception reception = new HotelReception();

		
		System.out.println("Enter the option:"
				+ "\n 1. Indian Menu"
				+ "\n 2. Italian Menu");
		
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		
		if(choice == 1) {
        reception.getIndianMenu();
        return;
		}

		if(choice == 2) {
	        reception.getItalianMenu();
	        return;
		}
		
		System.out.println("Invalid choice");
		return;
        
	}

}
