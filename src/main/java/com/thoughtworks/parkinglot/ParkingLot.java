package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.ParkingLotSameCarException;
import com.thoughtworks.ParkingLotUnParkException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private int capacity;
    private Owner owner;
    List<Object> vehicles = new ArrayList<>();

    public ParkingLot(int capacity, Owner owner) { // TODO - owner is mandatory so null checks should not be required.
        this.capacity = capacity;
        this.owner = owner;
    }

    public boolean park(Object object) throws ParkingLotSameCarException, ParkingLotFullException {

        if (capacity > 0) {

            if (vehicles.contains(object)) {
                throw new ParkingLotSameCarException();
            }

            vehicles.add(object);
            if (vehicles.size() == capacity) {
                owner.isNotifyParkingLotFull();
            }
            return true;
        }
        throw new ParkingLotFullException();
    }


    @Override
    public String toString() {
        return "ParkingLot{" +
                "capacity=" + capacity +
                '}';
    }

    public Object unPark(Object object) throws ParkingLotUnParkException {
        if (vehicles.contains(object)) {
            vehicles.remove(object);

            if (vehicles.size() == capacity - 1) { // TODO - conditions can be extracted and named. CMD + OPTION + M
                owner.isNotifySpaceAvailable();
            }
            return object;
        }
        throw new ParkingLotUnParkException();
    }
}
