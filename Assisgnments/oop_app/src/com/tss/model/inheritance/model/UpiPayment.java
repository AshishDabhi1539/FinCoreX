package com.tss.model.inheritance.model;

import java.util.Scanner;

public class UpiPayment implements IPaymentMethod{
	
	private String upiId;

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

	public UpiPayment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pay(double amount) {
		// TODO Auto-generated method stub
		System.out.println("Paid rupees "+ amount+ " using UPI.");
		
	}

	@Override
	public void getDetails() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Enter UPI ID: ");
			upiId = scanner.nextLine();
			if (upiId.matches("^[\\w.-]+@[\\w]+$")) break;
			System.out.println("Invalid UPI ID. e.g., user123@upi");
		}
		
	}

}
