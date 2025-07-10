package com.tss.model;

public class ChairFactory implements IFurnitureFactory {
    public IFurniture create(IStyle style) {
        return new Chair(style);
    }
}

