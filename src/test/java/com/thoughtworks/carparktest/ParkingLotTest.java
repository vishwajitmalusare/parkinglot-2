package com.thoughtworks.carparktest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotTest {
    @Test
    void givenSpaceAvailable_WhenPark_ThenShouldPark(){
        ParkingLot parkingLot = new ParkingLot();

        Assertions.assertTrue(parkingLot.park(new Object()));
    }
}
