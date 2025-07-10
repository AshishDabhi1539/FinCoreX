package com.tss.creational.abstractfactory.model;

public class ModernTable implements IFurniture {
	
	@Override
    public void describe() {
        System.out.println("Modern Table: Glass top with steel frame.");
    }
}

