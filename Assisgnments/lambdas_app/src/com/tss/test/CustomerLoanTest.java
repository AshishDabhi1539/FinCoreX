package com.tss.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.tss.model.Customer;

public class CustomerLoanTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Supplier<List<Customer>> customerSupplier = () -> Arrays.asList(
				new Customer("Rahul", 30, 40000, 650),
	            new Customer("Sneha", 28, 50000, 750),
	            new Customer("Mahek", 20, 30000, 700),   
	            new Customer("Dharmi", 25, 20000, 680),  
	            new Customer("Harshad", 32, 45000, 600)    
	        );
		
		 Predicate<Customer> ifCustomerEligible = customer ->
         	customer.getCustomerAge() >= 21 && customer.getCustomerIncome() >= 25000 && customer.getCustomerCreditScore() >= 650;
         	
         Function<Customer, Double> customerLoanCalculator = customer ->
            customer.getCustomerIncome() * (customer.getCustomerCreditScore() / 1000.0);	
            
         Consumer<Customer> customerLoanReceiptPrinter = customer -> {
            double loanAmount = customerLoanCalculator.apply(customer);
            System.out.println("Loan Approved for " + customer.getCustomerName());
            System.out.println("Eligible Amount: ₹" + loanAmount);
            System.out.println();
         };
         
         List<Customer> customers = customerSupplier.get();
         
         for (Customer customer : customers) {
             if (ifCustomerEligible.test(customer)) {
            	 customerLoanReceiptPrinter.accept(customer);
             }
	}
	}

}
