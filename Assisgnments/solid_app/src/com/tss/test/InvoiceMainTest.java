package com.tss.test;

import com.tss.model.InvoiceCalculator;
import com.tss.model.InvoicePrinter;

public class InvoiceMainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 InvoiceCalculator invoice = new InvoiceCalculator(101, "Laptop Purchase", 50000, 10);
	        InvoicePrinter printer = new InvoicePrinter();
	        printer.printToConsole(invoice);
	}

}
