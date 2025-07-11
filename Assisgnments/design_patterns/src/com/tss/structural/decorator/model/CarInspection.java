package com.tss.structural.decorator.model;

public class CarInspection implements ICarService {
    @Override
    public double getCost() {
        return 1000;
    }
}
