package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.ParkingLotSameCarException;
import com.thoughtworks.ParkingLotVehicleNotParkException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private int capacity;
    private int spaceAvailable;
    private Owner owner;
    List<Object> vehicles = new ArrayList<>();

    public ParkingLot(int capacity, Owner owner) {
        this.capacity = capacity;
        this.spaceAvailable = capacity;
        this.owner = owner;
    }

    public boolean park(Object object) throws ParkingLotSameCarException, ParkingLotFullException {

        if (spaceAvailable > 0) {
            if (vehicles.contains(object)) {
                throw new ParkingLotSameCarException("You cannot park same vehicle");
            }
            vehicles.add(object);
            if (vehicles.size() == capacity) {
                owner.isNotify();
            }
            spaceAvailable--;
            return true;
        }
        throw new ParkingLotFullException("parking lot is full");
    }


    @Override
    public String toString() {
        return "ParkingLot{" +
                "capacity=" + capacity +
                '}';
    }

    public Object unPark(Object object) throws ParkingLotVehicleNotParkException {
        if (vehicles.contains(object)) {
            vehicles.remove(object);
            return object;
        }
        throw new ParkingLotVehicleNotParkException("Vehicle not parked, unable to un-park");
    }
}
