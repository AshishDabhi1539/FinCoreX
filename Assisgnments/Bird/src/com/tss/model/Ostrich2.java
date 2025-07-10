package com.tss.model;

public class Ostrich2 implements IBird, IWalkable {
    public void walk() {
        System.out.println("Ostrich walks");
    }

    public void getColor() {
        System.out.println("Color: Black and White");
    }
}

