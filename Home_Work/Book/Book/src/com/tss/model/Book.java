package com.tss.model;

import java.io.Serializable;

public class Book implements Serializable{
    private static final long serialVersionUID = 1L;

    private int bookId;
    private String bookTitle;
    private String bookAuthor;
    private boolean isBookIssued;

    public Book(int bookId, String bookTitle, String bookAuthor) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.isBookIssued = false;
    }

    public int getBookId()
    {
        return bookId;
    }
    public String getBookTitle()
    {
        return bookTitle;
    }
    public String getBookAuthor()
    {
        return bookAuthor;
    }

    public boolean checkIsBookIssued()
    {
        return isBookIssued;
    }

    public void setIsBookIssued(boolean value)
    {
        this.isBookIssued = value;
    }
   
    public void display() {
        System.out.println("ID      : " + bookId);
        System.out.println("Title   : " + bookTitle);
        System.out.println("Author  : " + bookAuthor);
        System.out.println("Issued? : " + (isBookIssued ? "Yes" : "No"));
        System.out.println("----------------------------------");
    }
}
