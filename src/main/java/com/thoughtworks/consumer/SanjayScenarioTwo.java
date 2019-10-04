package com.thoughtworks.consumer;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.ParkingLotSameCarException;
import com.thoughtworks.parkinglot.ParkingLot;

public class SanjayScenarioTwo{

    public void park(ParkingLot parkingLotOne, ParkingLot parkingLotTwo) {
        Object carOne = new Object();

        try {
            parkingLotOne.park(carOne);
            parkingLotOne.park(carOne);
        } catch (ParkingLotSameCarException | ParkingLotFullException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        SanjayScenarioTwo sanjayScenarioTwo = new SanjayScenarioTwo();
        sanjayScenarioTwo.park(new ParkingLot(2), new ParkingLot(3));
    }
}
