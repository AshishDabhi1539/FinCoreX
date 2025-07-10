package com.tss.model;

public class Book {

	private int bookId;
	private String bookName;
	private String bookAuthor;
	private String bookPublication;
	private int price;
	
	
	public Book() {
		bookId = 1;
		bookName = "Harry Potter";
		bookAuthor = "J.K.Rowling";
		bookPublication = "Bloomsbury";
		price = 500;
		
	}
	
	public Book(int bookId, String bookName, String bookAuthor, String bookPublication, int price) {
		this.bookId = bookId;
		this.bookAuthor = bookAuthor;
		this.bookName = bookName;
		this.bookPublication = bookPublication;
		this.price = price;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
	public void setBookPublication(String bookPublication) {
		this.bookPublication = bookPublication;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getBookId() {
		return bookId;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public String getBookAuthor() {
		return bookAuthor;
	}
	
	public String getBookPublication() {
		return bookPublication;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void displayBookDetails() {
		System.out.println("The book id is: "+bookId);
		System.out.println("The name of the book is: "+bookName);
		System.out.println("The author of the book is: "+bookAuthor);
		System.out.println("The name of the publication: "+bookPublication);
		System.out.println("The price of the book is: "+price);
		
		discountedPriceIs(price);
	}

	private void discountedPriceIs(int price) {
		// TODO Auto-generated method stub
		double discountPrice = price * 0.9;
		
		System.out.println("Discounted Price: "+discountPrice);
		
	}
}
