package com.tss.model.inheritance.model;

import java.util.Scanner;

public class PayPalPayment implements IPaymentMethod{
	
	private String email;
	
	

	public PayPalPayment(String email) {
	
		this.email = email;
	}



	public PayPalPayment() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public void pay(double amount) {
		// TODO Auto-generated method stub
		System.out.println("Paid rupees "+ amount+ " using PayPal.");
		
	}



	@Override
	public void getDetails() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Enter PayPal Email: ");
			email = scanner.nextLine();
			if (email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) break;
			System.out.println("Invalid email. e.g., name@example.com");
		}
	}

}
