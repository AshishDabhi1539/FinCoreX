package com.tss.creational.facade.model;

public class IndianHotel implements IHotel{

	@Override
	public IMenu getMenu() {
		// TODO Auto-generated method stub
		return new IndianMenu();
	}

}
