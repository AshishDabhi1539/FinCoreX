package com.tss.model;

public class TaxCalculator2 {

	private ILogger iLogger;
	
	 public TaxCalculator2(ILogger logger) {
	        this.iLogger = logger;
	    }

	    public int calculateTax(int amount, int rate) {
	        int tax = 0;
	        try {
	            tax = amount / rate;
	        } catch (Exception e) {
	            iLogger.log("Divide by zero");
	        }
	        return tax;
	    }
}
