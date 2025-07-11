package com.tss.structural.decorator.model;

public abstract class CarServiceDecorator implements ICarService {
    protected ICarService car;

    public CarServiceDecorator(ICarService car) {
        this.car = car;
    }

    @Override
    public double getCost() {
        return car.getCost();
    }
}
