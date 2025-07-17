package com.tss.customer;

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String password;

    public Customer(String name, int age, String address, String password) {
        this.name = name;
        this.age = age;
//        this.gender = gender;
//        this.email = email;
//        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    // Getters and Setters

    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // Override toString() if needed
}
