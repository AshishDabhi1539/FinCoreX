package com.tss.creational.abstractfactory.model;

public class MahindraFactory implements ICarFactory {
    @Override
    public ICars makeCar() {
        return new Mahindra();
    }
}
