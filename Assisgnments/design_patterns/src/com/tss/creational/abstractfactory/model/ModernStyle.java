package com.tss.creational.abstractfactory.model;

public class ModernStyle implements IStyle {
    public IFurniture createChair() {
        return new ModernChair();
    }

    public IFurniture createSofa() {
        return new ModernSofa();
    }

    public IFurniture createTable() {
        return new ModernTable();
    }
}

