package com.thoughtworks.consumer;

import com.thoughtworks.ParkingLotSameCarException;
import com.thoughtworks.parkinglot.*;

public class SanjayScenerioOne{
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
        SanjayScenerioOne sanjayScenerioOne = new SanjayScenerioOne();
        sanjayScenerioOne.park(new ParkingLot(2), new ParkingLot(3));
    }
}
