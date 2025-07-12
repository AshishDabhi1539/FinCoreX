package com.tss.model;

import java.io.Serializable;

public class Movie implements Serializable {


	private static final long serialVersionUID = 1L;
	public String id, name, genre;
	public int year;

	public Movie(String name, String genre, int year) {
		if (!genre.matches("[a-zA-Z]+")) {
			throw new IllegalArgumentException("Genre must contain only letters.");
		}
		if (String.valueOf(year).length() != 4) {
			throw new IllegalArgumentException("Year must be a 4-digit number.");
		}
		this.name = name;
		this.genre = genre;
		this.year = year;
		this.id = generateId(name, genre, year);
	}

	private String generateId(String name, String genre, int year) {
		String yearStr = String.valueOf(year);
		return name.substring(0, 2) + genre.substring(0, 2) + yearStr.substring(yearStr.length() - 2);
	}

	@Override
	public String toString() {
		return "ID: " + id + ", Name: " + name + ", Genre: " + genre + ", Year: " + year;
	}
}
