package com.tss.model;

public class Labour implements IWorker{

	@Override
	public void startWork() {
		// TODO Auto-generated method stub
		System.out.println("Please start working!");
	}

	@Override
	public void stopWork() {
		// TODO Auto-generated method stub
		System.out.println("Please stop working!");
		
	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("Please eat food!");
	}

	@Override
	public void drink() {
		// TODO Auto-generated method stub
		System.out.println("Please drink water!");
	}

}
