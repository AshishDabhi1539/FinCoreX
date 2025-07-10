package com.tss.model;

import com.tss.ExceptionClass.InsufficientWaterException;
import com.tss.ExceptionClass.OverfillException;

public class WaterDispenser {

    private int currentWaterLevel = 0;

    public void fillWater(int waterFill) {
        if (currentWaterLevel + waterFill > 100) {
            throw new OverfillException("Cannot fill water. Exceeds tank capacity. Current level: " 
                + currentWaterLevel + "L, Attempted to add: " + waterFill + "L.");
        }
        currentWaterLevel += waterFill;
        System.out.println("Successfully filled " + waterFill + "L of water.");
    }

    public void dispenseWater(int waterAmount) {
        if (waterAmount > currentWaterLevel) {
            throw new InsufficientWaterException("Cannot dispense. Not enough water. Current level: " 
                + currentWaterLevel + "L, Requested: " + waterAmount + "L.");
        }
        currentWaterLevel -= waterAmount;
        System.out.println("Successfully dispensed " + waterAmount + "L of water.");
    }

    public int getCurrentWaterLevel() {
        return currentWaterLevel;
    }
}
