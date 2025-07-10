package com.tss.creational.factory.model;

import com.tss.creational.abstractfactory.model.ICars;

public class Maruti implements ICars{

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Start maruti car");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Stop maruti car");
	}

}
