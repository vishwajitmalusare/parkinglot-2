package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotFullException;
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

    public void park(Object object) throws ParkingLotSameCarException, ParkingLotFullException {

        if (isAlreadyParked(object)) {
            throw new ParkingLotSameCarException();
        }

        if (isFull(capacity)) {
            throw new ParkingLotFullException();
        }
        vehicles.add(object);
        for (int i = 0; i < observers.size(); i++) {
            ParkingLotAuthority observer = observers.get(i);
            if (isFull(capacity)) {
                observer.isNotifyParkingLotFull();
            }
        }
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

            for (int i = 0; i < observers.size(); i++) {
                ParkingLotAuthority observer = observers.get(i);
                if (isFull(capacity - 1)) {
                    observer.isNotifySpaceAvailable();
                }
            }
            return object;
        }
        throw new ParkingLotUnParkException();
    }
}
