package com.tss.creational.abstractfactory.model;

import com.tss.creational.abstractfactory.model.ICars;

public class Mahindra implements ICars{

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Mahindra starts");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Mahindra stops");
	}

}
