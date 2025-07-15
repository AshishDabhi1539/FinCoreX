package com.tss.customer;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String password;
	private int age;
	private String gender;
	private String email;
	private String phone;
	private String address;

	public Customer(String name, String password, int age, String gender, String email, String phone, String address) {
		this.name = name;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "Customer: " + name + ", Age: " + age + ", Email: " + email + ", Phone: " + phone;
	}
}
