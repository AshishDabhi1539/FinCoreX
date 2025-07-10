package com.tss.model.inheritance.test;

import java.util.Scanner;

import com.tss.model.inheritance.model.CreditCardPayment;
import com.tss.model.inheritance.model.DebitCardPayment;
import com.tss.model.inheritance.model.IPaymentMethod;
import com.tss.model.inheritance.model.PayPalPayment;
import com.tss.model.inheritance.model.UpiPayment;

public class PaymentTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
        System.out.println("Enter amount to pay:");
        double amount = scanner.nextDouble();
        
        scanner.nextLine(); 
        
        
        
        System.out.println("Choose payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. UPI ");
        System.out.println("4. PayPal");
        
        System.out.println("Enter the coice:");
        int choice = scanner.nextInt();
        
        IPaymentMethod paymentMethod = null;
        
        switch (choice) {
        case 1:
            paymentMethod = new CreditCardPayment();
            
            
            break;

        case 2:
            paymentMethod = new DebitCardPayment();
            
            break;

        case 3:
        	paymentMethod = new UpiPayment();
        	
            break;
            
        case 4:
        	paymentMethod = new PayPalPayment();
        	
            break;

        default:
            System.out.println("Invalid choice");
            
            return;
    }
    paymentMethod.getDetails();

    paymentMethod.pay(amount);
    
    
        

	}

}
