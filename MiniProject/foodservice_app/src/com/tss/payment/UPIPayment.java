package com.tss.payment;

import java.util.Scanner;

public class UPIPayment extends PaymentDecorator {
    public UPIPayment(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        super.pay(amount);
        try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter UPI ID: ");
			String upi = sc.nextLine();
			System.out.println("UPI payment successful with " + upi);
		}
    }
}
