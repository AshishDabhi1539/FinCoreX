package com.tss.test;

import com.tss.model.CreditCard2;
import com.tss.model.IPayment;
import com.tss.model.ShoppingCart2;
import com.tss.model.UpiPayment;

public class ShoppingCartTest2 {

	 public static void main(String[] args) {
	        IPayment paymentMethod1 = new CreditCard2();
	        IPayment paymentMethod2 = new UpiPayment();

	        ShoppingCart2 cart1 = new ShoppingCart2(paymentMethod1);
	        cart1.checkout(500);

	        ShoppingCart2 cart2 = new ShoppingCart2(paymentMethod2);
	        cart2.checkout(1000);
	    }
}
