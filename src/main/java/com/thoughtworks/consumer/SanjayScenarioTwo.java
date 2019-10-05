package com.thoughtworks.consumer;

import com.thoughtworks.ParkingLotSameCarException;
import com.thoughtworks.parkinglot.ParkingLot;
import com.thoughtworks.parkinglot.ParkingLotAuthority;

import java.util.ArrayList;
import java.util.List;

public class SanjayScenarioTwo {

    public void park(ParkingLot parkingLotOne, ParkingLot parkingLotTwo) {
        Object carOne = new Object();

        try {
            parkingLotOne.park(carOne);
            parkingLotOne.park(carOne);
        } catch (ParkingLotSameCarException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        SanjayScenarioTwo sanjayScenarioTwo = new SanjayScenarioTwo();
        List<ParkingLotAuthority> owner = new ArrayList<>();
        sanjayScenarioTwo.park(new ParkingLot(2, owner), new ParkingLot(3, owner));
    }
}
