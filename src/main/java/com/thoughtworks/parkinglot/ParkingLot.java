package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.ParkingLotSameCarException;
import com.thoughtworks.ParkingLotVehicalNotParkException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private int capacity;
    private int spaceAvailable;
    List<Object> vehicles = new ArrayList<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.spaceAvailable = capacity;
    }

    public boolean park(Object object) throws ParkingLotSameCarException, ParkingLotFullException {
        if (spaceAvailable > 0) {
            if (vehicles.contains(object)) {
                throw new ParkingLotSameCarException("You cannot park same vehicle");
            }
            vehicles.add(object);
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

    public boolean unPark(Object car) throws ParkingLotVehicalNotParkException {
        if (vehicles.contains(car)) {
            vehicles.remove(car);
            return true;
        }
        if(vehicles.isEmpty()){
            throw new ParkingLotVehicalNotParkException("the parking lot has no car");
        }
        throw new ParkingLotVehicalNotParkException("the car may not be parked here");
    }

}
