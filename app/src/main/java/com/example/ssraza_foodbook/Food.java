package com.example.ssraza_foodbook;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Food implements Serializable {

    private String description;
    private Map<String, Integer> bestBeforeDate;
    private String location;
    private int count;
    private int unitCost;

    public Food(
            String description,
            Map<String, Integer> bestBeforeDate,
            String location,
            int count,
            int unitCost
    ) {
        this.description = description;
        this.bestBeforeDate = bestBeforeDate;
        this.location = location;
        this.count = count;
        this.unitCost = unitCost;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(int unitCost) {
        this.unitCost = unitCost;
    }

    public Map<String, Integer> getBestBeforeDate() {
        return bestBeforeDate;
    }

    public void setBestBeforeDate(Map<String, Integer> bestBeforeDate) {
        this.bestBeforeDate = bestBeforeDate;
    }
}
