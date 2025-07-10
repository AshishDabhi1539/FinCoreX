package com.tss.creational.abstractfactory.model;

public class ModernSofa implements IFurniture {
	
	@Override
    public void describe() {
        System.out.println("Modern Sofa: Low-profile with neutral fabric.");
    }
}
