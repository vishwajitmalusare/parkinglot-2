package com.thoughtworks.carparktest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() {
        ParkingLot parkingLot = new ParkingLot(1 ); //this represent available lots

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotPark() {
        ParkingLot parkingLot = new ParkingLot(1); // spaceAvailable = 1
        parkingLot.park(new Object()); // spaceAvailable--

        assertFalse(parkingLot.park(new Object()));
    }
}
