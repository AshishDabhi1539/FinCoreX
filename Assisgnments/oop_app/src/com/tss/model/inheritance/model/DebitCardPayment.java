package com.tss.model.inheritance.model;

import java.util.Scanner;
public class DebitCardPayment implements IPaymentMethod{

	private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    
    public DebitCardPayment(String cardNumber, String cardHolderName, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }
	public DebitCardPayment() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void pay(double amount) {
		// TODO Auto-generated method stub
		System.out.println("Paid rupees "+ amount + "using Debit Card.");
	}
	
	@Override
	public void getDetails() {
		
		Scanner scanner = new Scanner(System.in);
		// TODO Auto-generated method stub
		while (true) {
			System.out.print("Enter 16-digit Debit Card Number: ");
			cardNumber = scanner.nextLine();
			if (cardNumber.matches("(\\d{4}[- ]?){3}\\d{4}"))break;
			System.out.println("Invalid card number.");
		}

		while (true) {
			System.out.print("Enter Card Holder Name: ");
			cardHolderName = scanner.nextLine();
			if (cardHolderName.matches("[a-zA-Z ]+")) break;
			System.out.println("Invalid name. Alphabets and space only.");
		}
        
		
		while (true) {
			System.out.print("Enter 3-digit CVV: ");
			cvv = scanner.nextLine();
			if (cvv.matches("\\d{3}")) break;
			System.out.println("Invalid CVV.");
		}
	}
	
	
	
    
}
