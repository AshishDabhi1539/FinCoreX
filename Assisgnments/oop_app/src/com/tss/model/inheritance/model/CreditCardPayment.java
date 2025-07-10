package com.tss.model.inheritance.model;

import java.util.Scanner;
public class CreditCardPayment implements IPaymentMethod{

	private String creditCardNumber;
    private String creditCardName;
    private String expiryDate;
    private String creditCardCvv;
    
    public CreditCardPayment(String creditCardNumber, String creditCardCvv, String creditCardName, String expiryDate) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardName = creditCardName;
        this.creditCardCvv = creditCardCvv;
        this.expiryDate = expiryDate;
    }
    
	public CreditCardPayment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pay(double amount) {
		// TODO Auto-generated method stub
		 System.out.println("Paid rupees" + amount + " using Credit Card.");
		
	}

	@Override
	public void getDetails() {
		
		Scanner scanner = new Scanner(System.in);
		// TODO Auto-generated method stub
		
        
        while (true) {
        	System.out.print("Enter Card Number: ");
            String creditCardNumber = scanner.nextLine();
			if (creditCardNumber.matches("\\d{16}")) break;
			System.out.println("Invalid card number.");
        }
        
        while (true) {
        	System.out.print("Enter Card Holder Name: ");
            String creditCardName = scanner.nextLine();
			if (creditCardName.matches("[a-zA-Z ]+")) break;
			System.out.println("Invalid name. Alphabets and space only.");
		}
        
        
        scanner.nextLine();
        while(true) {
        	System.out.print("Enter CVV: ");
            String creditCardCvv = scanner.nextLine();
            if(creditCardCvv.matches("\\d{3}"))break;
            System.out.println("Enter the valid 3 digit CVV");
        }
        
    

		
	}

	

}
