package com.tss.model;

public class Sparrow2 implements IBird, IFlyable, IWalkable {
    public void fly() {
        System.out.println("Sparrow flies");
    }

    public void walk() {
        System.out.println("Sparrow walks");
    }

    public void getColor() {
        System.out.println("Color: Brown");
    }
}

