package com.tss.structural.adaptor.test;
import java.util.Scanner;

import com.tss.structural.adaptor.model.Biscuit;
import com.tss.structural.adaptor.model.Chocolate;
import com.tss.structural.adaptor.model.Hat;
import com.tss.structural.adaptor.model.HatAdaptor;
import com.tss.structural.adaptor.model.ShoppingCart;

public class ItemTest {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		ShoppingCart cart = new ShoppingCart();
		
		while (true) {
            System.out.println("Shopping Cart Menu");
            System.out.println("1. Add Biscuit");
            System.out.println("2. Add Chocolate");
            System.out.println("3. Add Hat");
            System.out.println("4. View Cart and Checkout");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    cart.addItemsToCart(new Biscuit("Biscuit", 10));
                    System.out.println("Biscuit added to cart.");
                    break;
                case 2:
                    cart.addItemsToCart(new Chocolate("Chocolate", 20));
                    System.out.println("Chocolate added to cart.");
                    break;
                case 3:
                    cart.addItemsToCart(new HatAdaptor(new Hat("Hat", "Long", 50, 7)));
                    System.out.println("Hat added to cart.");
                    break;
                case 4:
                    
                    cart.displayCart();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
	}

}
