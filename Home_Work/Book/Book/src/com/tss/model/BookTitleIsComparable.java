package com.tss.model;

import java.util.Comparator;

public class BookTitleIsComparable implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o2.getBookTitle().compareToIgnoreCase(o1.getBookTitle());
    }
}