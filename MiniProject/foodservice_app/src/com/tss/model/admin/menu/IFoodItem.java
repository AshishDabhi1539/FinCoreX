package com.tss.model.admin.menu;

import java.io.Serializable;

public interface IFoodItem extends Serializable {
	String getName();

	double getPrice();

	void setPrice(double price);
}
