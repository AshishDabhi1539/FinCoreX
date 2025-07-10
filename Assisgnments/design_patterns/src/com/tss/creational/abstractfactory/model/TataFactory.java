package com.tss.creational.abstractfactory.model;

public class TataFactory implements ICarFactory {
    @Override
    public ICars makeCar() {
        return new Tata();
    }
}
