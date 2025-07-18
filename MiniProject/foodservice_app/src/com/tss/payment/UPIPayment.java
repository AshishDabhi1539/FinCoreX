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
            String upiId;
            while (true) {
                System.out.print("Enter UPI ID (e.g., username@bank): ");
                upiId = sc.nextLine().trim();

                if (!isValidUpi(upiId)) {
                    System.out.println("Invalid UPI ID format. Please use 'username@bank' format.");
                    continue;
                }

                break;
            }

            String maskedUpi = maskUpi(upiId);
            System.out.println("UPI payment successful with " + maskedUpi);
        }
    }

    private boolean isValidUpi(String upi) {
        
        return upi.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z]+$");
    }

    private String maskUpi(String upi) {
        int atIndex = upi.indexOf("@");
        if (atIndex <= 1) return "****" + upi.substring(atIndex); 
        return upi.substring(0, 2) + "****" + upi.substring(atIndex);
    }
}
