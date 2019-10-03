package com.thoughtworks.carparktest;

public class ParkingLot {

    private final int capacity;
    private int spaceAvailable;

    ParkingLot(int capacity) {
        this.capacity = capacity;
        this.spaceAvailable = capacity;
    }

    public boolean park(Object object) {
        if (spaceAvailable > 0) {
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
