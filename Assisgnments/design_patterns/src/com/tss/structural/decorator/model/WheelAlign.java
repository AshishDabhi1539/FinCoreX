package com.tss.structural.decorator.model;

public class WheelAlign extends CarServiceDecorator {
    public WheelAlign(ICarService car) {
        super(car);
    }

    @Override
    public double getCost() {
        return 400 + super.getCost();
    }
}

