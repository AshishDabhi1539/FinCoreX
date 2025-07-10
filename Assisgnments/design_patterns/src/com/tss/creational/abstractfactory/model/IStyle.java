package com.tss.creational.abstractfactory.model;

public interface IStyle {
    IFurniture createChair();
    IFurniture createSofa();
    IFurniture createTable();
}
