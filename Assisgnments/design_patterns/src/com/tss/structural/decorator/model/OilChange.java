package com.tss.structural.decorator.model;

public class OilChange extends CarServiceDecorator {
    public OilChange(ICarService car) {
        super(car);
    }

    @Override
    public double getCost() {
        return 500 + super.getCost();
    }
}

