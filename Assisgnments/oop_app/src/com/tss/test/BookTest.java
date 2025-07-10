package com.tss.test;

import java.util.Scanner;

import com.tss.model.Book;

public class BookTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		
		Book book1 = new Book();
		displayBookDetails(book1, scanner);
		
	}

	private static Book displayBookDetails(Book book, Scanner scanner) {
		// TODO Auto-generated method stub
		Book book1 = null;
		System.out.println("Enter the book id:");
		int bookId = scanner.nextInt();
		book.setBookId(bookId);
	
		scanner.nextLine();
		System.out.println("Enter the book name: ");
		String bookName = scanner.nextLine();	
		book.setBookName(bookName);
		
		System.out.println("Enter the author of the book: ");
		String bookAuthor = scanner.nextLine();
		book.setBookAuthor(bookAuthor);
		
		System.out.println("Enter the publication of the book: ");
		String bookPublication = scanner.nextLine();
		book.setBookPublication(bookPublication);
		
		System.out.println("Enter the price of the book: ");
		int price = scanner.nextInt();
		book.setPrice(price);
		
		book.displayBookDetails();
		
		return book;
	}

}
