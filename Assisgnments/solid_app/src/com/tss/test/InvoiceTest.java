package com.tss.test;

import com.tss.model.invoice;

public class InvoiceTest {
    public static void main(String[] args) {
        invoice invoice1 = new invoice(1, "Laptop", 800000.0, 10.0);

        System.out.println("Invoice");
        System.out.println("ID: " + invoice1.getId());
        System.out.println("Description: " + invoice1.getDescription());
        System.out.println("Cost: ₹" + invoice1.getCost());
        System.out.println("Tax (" + invoice.TAX_PERCENT + "%): ₹" + invoice1.calculateTax());
        System.out.println("Discount (" + invoice1.getDiscountPercent() + "%): ₹" + invoice1.calculateDiscount());
        System.out.println("Final Amount: ₹" + invoice1.calculateFinalCost());

        System.out.println("\nPrinting full invoice:\n");
        invoice1.print();  
    }
}

