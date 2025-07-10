package com.tss.model;

public class SofaFactory implements IFurnitureFactory {
    public IFurniture create(IStyle style) {
        return new Sofa(style);
    }
}
