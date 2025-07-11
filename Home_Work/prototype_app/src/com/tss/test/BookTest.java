package com.tss.test;

import com.tss.model.Book;

public class BookTest {

	public static void main(String[] args) {
		

		Book original = new Book("Alchemy of souls", "Gang of Four", 550.0);
        original.display();

        
        Book discountedCopy = original.clone();
        discountedCopy.setPrice(300.0);

        System.out.println("Cloned Book:");
        discountedCopy.display();
	}

}
