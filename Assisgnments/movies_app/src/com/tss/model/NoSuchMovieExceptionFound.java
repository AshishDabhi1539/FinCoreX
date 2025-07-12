package com.tss.model;

public class NoSuchMovieExceptionFound extends Exception{

	public NoSuchMovieExceptionFound(String message) {
        System.out.println("NO such movie found. ");
    }
	
}
