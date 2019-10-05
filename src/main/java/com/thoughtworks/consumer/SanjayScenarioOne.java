package com.thoughtworks.consumer;

import com.thoughtworks.ParkingLotSameCarException;
import com.thoughtworks.parkinglot.ParkingLot;
import com.thoughtworks.parkinglot.ParkingLotAuthority;

import java.util.ArrayList;
import java.util.List;

public class SanjayScenarioOne {
    public void park(ParkingLot parkingLotOne, ParkingLot parkingLotTwo) {
        Object carOne = new Object();
        Object carTwo = new Object();
        Object carThree = new Object();

        try {
            parkingLotOne.park(carOne);
            parkingLotOne.park(carTwo);
            parkingLotTwo.park(carThree);
        } catch (ParkingLotSameCarException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        SanjayScenarioOne sanjayScenarioOne = new SanjayScenarioOne();
        List<ParkingLotAuthority> owner = new ArrayList<>();
        sanjayScenarioOne.park(new ParkingLot(2, owner), new ParkingLot(3, owner));
    }
}
