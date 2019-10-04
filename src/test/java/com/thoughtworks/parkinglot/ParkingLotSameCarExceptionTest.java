package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.ParkingLotSameCarException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotSameCarExceptionTest {
    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() throws ParkingLotSameCarException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1); //this represent available lots

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotPark() throws ParkingLotSameCarException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1); // spaceAvailable = 1
        parkingLot.park(new Object()); // spaceAvailable - -

        //assertFalse(parkingLot.park(new Object()));

        ParkingLotSameCarException exception = assertThrows(ParkingLotSameCarException.class, () -> {
            parkingLot.park(new Object());

        });
        assertEquals("parking lot is full", exception.getMessage());
    }

    @Test
    void givenParkingSameObject_WhenPark_ThenShouldNotPark() throws ParkingLotSameCarException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2);

        Object object = new Object();
        parkingLot.park(object);
        ParkingLotSameCarException exception = assertThrows(ParkingLotSameCarException.class, () -> {
            parkingLot.park(object);

        });
        assertEquals("You cannot park same vehicle", exception.getMessage());
    }

    @Test
    void givenAlreadyParkedCar_WhenUnPark_thenShouldBeAbleToUnPark() throws ParkingLotSameCarException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object alreadyParkedCar = new Object();
        parkingLot.park(alreadyParkedCar);

        assertTrue(parkingLot.unPark(alreadyParkedCar));
    }

    @Test
    void givenCarWhichIsNotParkedIn_WhenUnPark_thenShouldBeNotAbleToUnPark() throws ParkingLotSameCarException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object CarWhichIsNotParkedIn = new Object();
        Object CarWhichIsParked = new Object();
        parkingLot.park(CarWhichIsParked);

        ParkingLotSameCarException exception = assertThrows(ParkingLotSameCarException.class, () -> {
            parkingLot.unPark(CarWhichIsNotParkedIn);
        });

        assertEquals("the car may not be parked here", exception.getMessage());
    }

    @Test
    void givenParkingLotIsEmpty_WhenUnPark_thenShouldNotBeAbleToUnPark() throws ParkingLotSameCarException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object AnyOtherCar = new Object();

        ParkingLotSameCarException exception = assertThrows(ParkingLotSameCarException.class, () -> {
            parkingLot.unPark(AnyOtherCar);
        });

        assertEquals("the parking lot has no car", exception.getMessage());
    }
}
