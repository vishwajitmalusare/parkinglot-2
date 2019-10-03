package com.thoughtworks.carparktest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotTest {
    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() {
        ParkingLot parkingLot = new ParkingLot(1);

        assertTrue(parkingLot.park(new Object()));
    }
}
