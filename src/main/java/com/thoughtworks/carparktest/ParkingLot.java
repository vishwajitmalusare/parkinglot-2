package com.thoughtworks.carparktest;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private final int capacity;
    private int spaceAvailable;
    List<Object> vehicles = new ArrayList<>();

    ParkingLot(int capacity) {
        this.capacity = capacity;
        this.spaceAvailable = capacity;
    }

    public boolean park(Object object) {
        if (spaceAvailable > 0) {
            if (vehicles.contains(object)) {
                return false;
            }
            vehicles.add(object);
            spaceAvailable--;
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "ParkingLot{" +
                "capacity=" + capacity +
                '}';
    }
}
