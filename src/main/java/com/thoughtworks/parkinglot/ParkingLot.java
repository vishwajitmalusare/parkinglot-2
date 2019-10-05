package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.ParkingLotSameCarException;
import com.thoughtworks.ParkingLotUnParkException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private int capacity;
    private ParkingLotAuthority owner;
    List<Object> vehicles = new ArrayList<>();

    public ParkingLot(int capacity, ParkingLotAuthority owner) { // TODO - owner is mandatory so null checks should not be required.
        this.capacity = capacity;
        this.owner = owner;
    }

    public boolean park(Object object) throws ParkingLotSameCarException, ParkingLotFullException {

        if (capacity > 0) {

            if (isAlreadyParked(object)) {
                throw new ParkingLotSameCarException();
            }

            vehicles.add(object);
            if (isFull(capacity)) {
                owner.isNotifyParkingLotFull();
            }
            return true;
        }
        throw new ParkingLotFullException();
    }

    private boolean isFull(int capacity) {
        return vehicles.size() == capacity;
    }

    private boolean isAlreadyParked(Object object) {
        return vehicles.contains(object);
    }


    @Override
    public String toString() {
        return "ParkingLot{" +
                "capacity=" + capacity +
                '}';
    }

    public Object unPark(Object object) throws ParkingLotUnParkException {
        if (isAlreadyParked(object)) {
            vehicles.remove(object);

            if (isFull(capacity - 1)) { // TODO - conditions can be extracted and named. CMD + OPTION + M
                owner.isNotifySpaceAvailable();
            }
            return object;
        }
        throw new ParkingLotUnParkException();
    }
}
