package com.tss.model;

public class TableFactory implements IFurnitureFactory {
    public IFurniture create(IStyle style) {
        return new Table(style);
    }
}
