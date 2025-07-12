package com.tss.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.tss.model.Book;
import com.tss.model.BookNameIsComparator;
import com.tss.model.BookTitleIsComparable;

public class BookTest {
    static final String bookFileName = "books.dat";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Book> books = loadBooks();

        while (true) {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Display Available Books");
            System.out.println("4. Display Issued Books");
            System.out.println("5. Return Book");
            System.out.println("6. Display All Books (Sorted)");
            System.out.println("7. Exit & Save");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: addTheBook(books); break;
                case 2: issuingTheBook(books); break;
                case 3: displayAllTheAvailableBooks(books); break;
                case 4: displayAllTheIssuedBooks(books); break;
                case 5: returnTheBook(books); break;
                case 6: displayTheBooks(books); break;
                case 7:
                    saveTheBooks(books);
                    System.out.println("Thank you!");
                    return;
                default: System.out.println("Enter a valid choice.");
            }
        }
    }

    @SuppressWarnings("unchecked")
	private static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bookFileName))) {
            books = (List<Book>) ois.readObject();
            System.out.println("Books loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved books found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
        return books;
    }

    private static void saveTheBooks(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bookFileName))) {
            oos.writeObject(books);
            System.out.println("Books saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    private static void addTheBook(List<Book> books) {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        for (Book book : books) {
            if (book.getBookId() == id) {
                System.out.println("Book ID already exists!");
                return;
            }
        }
        scanner.nextLine(); // consume newline
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    private static void issuingTheBook(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available to issue.");
            return;
        }
        System.out.print("Enter Book ID to issue: ");
        int id = scanner.nextInt();
        for (Book book : books) {
            if (book.getBookId() == id) {
                if (!book.checkIsBookIssued()) {
                    book.setIsBookIssued(true);
                    System.out.println("Book issued successfully!");
                } else {
                    System.out.println("Book already issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void returnTheBook(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books to return.");
            return;
        }
        System.out.print("Enter Book ID to return: ");
        int id = scanner.nextInt();
        for (Book book : books) {
            if (book.getBookId() == id && book.checkIsBookIssued()) {
                book.setIsBookIssued(false);
                System.out.println("Book returned successfully!");
                return;
            }
        }
        System.out.println("Book not found or not issued.");
    }

    private static void displayAllTheAvailableBooks(List<Book> books) {
        boolean found = false;
        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            if (!book.checkIsBookIssued()) {
                book.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available books.");
        }
    }

    private static void displayAllTheIssuedBooks(List<Book> books) {
        boolean found = false;
        System.out.println("\nIssued Books:");
        for (Book book : books) {
            if (book.checkIsBookIssued()) {
                book.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No issued books.");
        }
    }

    private static void displayTheBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books to display.");
            return;
        }

        System.out.println("1. Sort by Author (Ascending)");
        System.out.println("2. Sort by Title (Descending)");
        int opt = scanner.nextInt();
        if (opt == 1) {
            Collections.sort(books, new BookNameIsComparator());
        } else if (opt == 2) {
            Collections.sort(books, new BookTitleIsComparable());
        }

        for (Book book : books) {
            book.display();
        }
    }
}
