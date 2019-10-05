package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotSameCarException;
import com.thoughtworks.ParkingLotUnParkException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private int capacity;
    List<ParkingLotAuthority> observers;
    List<Object> vehicles = new ArrayList<>();

    public ParkingLot(int capacity, List<ParkingLotAuthority> observer) {
        this.capacity = capacity;
        this.observers = observer;
    }

    public void park(Object object) throws ParkingLotSameCarException {
        if (isFull()) {
            throw new ParkingLotSameCarException();
        }
        if (isAlreadyPark(object)) {
            throw new ParkingLotSameCarException();
        }
        vehicles.add(object);
        notifySubscriberIsFull();
    }

    public Object unPark(Object object) throws ParkingLotUnParkException {
        if (isAlreadyPark(object)) {
            vehicles.remove(object);

            notifySubscriberSpaceAvailable();
            return object;
        }
        throw new ParkingLotUnParkException();
    }


    private void notifySubscriberSpaceAvailable() {
        for (ParkingLotAuthority observer : observers) {
            if (vehicles.size() == capacity - 1) {
                observer.isNotifySpaceAvailable();
            }
        }
    }


    private void notifySubscriberIsFull() {
        for (ParkingLotAuthority observer : observers) {
            if (isFull()) {
                observer.isNotifyParkingLotFull();
            }
        }
    }

    private boolean isAlreadyPark(Object object) {
        return vehicles.contains(object);
    }

    private boolean isFull() {
        return vehicles.size() == capacity;
    }


    @Override
    public String toString() {
        return "ParkingLot{" +
                "capacity=" + capacity +
                '}';
    }

}
