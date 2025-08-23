package com.tss.ioc.entity;

import org.springframework.beans.factory.annotation.Value;

public class Harddisk {

	@Value("90")
	private int capacity;

	public Harddisk(int capacity) {
		super();
		this.capacity = capacity;
	}

	public Harddisk() {
		super();
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
