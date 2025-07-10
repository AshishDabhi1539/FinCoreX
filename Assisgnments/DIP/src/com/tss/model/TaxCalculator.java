package com.tss.model;

public class TaxCalculator{

	private DBLogger dbLogger;

	public TaxCalculator(DBLogger dbLogger) {
	
		this.dbLogger = dbLogger;
	} 
	
	

	public int calculateTax(int amount, int rate) {
		int tax = 0;
		try {
            tax = amount / rate;
        } catch (Exception e) {
            dbLogger.log("Divide by zero");
        }
        return tax;
	}
}
