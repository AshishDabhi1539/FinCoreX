package com.tss.model;

public class MovieStoreFullException extends Exception {
    public MovieStoreFullException(String message) {
        System.out.println("Movie store is full. Cannot enter new movies in it.");
    }
}