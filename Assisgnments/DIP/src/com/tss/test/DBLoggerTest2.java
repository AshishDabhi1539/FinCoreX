package com.tss.test;

import com.tss.model.DBLogger2;
import com.tss.model.ILogger;
import com.tss.model.TaxCalculator2;

public class DBLoggerTest2 {

	public static void main(String args[]) {
		ILogger logger = new DBLogger2();
		TaxCalculator2 taxCalculator = new TaxCalculator2(logger);

		int tax = taxCalculator.calculateTax(1000, 0);
		System.out.println("Tax: " + tax);
	}
}