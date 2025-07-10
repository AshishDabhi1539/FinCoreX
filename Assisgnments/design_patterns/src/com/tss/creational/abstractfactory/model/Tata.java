package com.tss.creational.abstractfactory.model;

import com.tss.creational.abstractfactory.model.ICars;

public class Tata implements ICars{

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Tata starts");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Tata stops");
	}

}
