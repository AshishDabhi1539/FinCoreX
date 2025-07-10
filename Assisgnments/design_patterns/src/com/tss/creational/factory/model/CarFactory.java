package com.tss.creational.factory.model;

import com.tss.creational.abstractfactory.model.ICars;

public class CarFactory {

	public ICars makeCars(CarType car) {
		if (car == CarType.Maruti) {
			return new Maruti();
		} else if (car == CarType.Tata) {
			return new Tata();
		} else if (car == CarType.Mahindra) {
			return new Mahindra();
		}
		return null;
	}
}
