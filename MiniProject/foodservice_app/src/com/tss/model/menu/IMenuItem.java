package com.tss.model.menu;

import java.io.Serializable;

public interface IMenuItem extends Serializable {
    String getId();
    String getName();
    String getDescription();
    double getPrice();
}
