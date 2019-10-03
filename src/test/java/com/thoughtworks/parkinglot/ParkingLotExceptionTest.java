package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotExceptionTest {
    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1); //this represent available lots

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(1); // spaceAvailable = 1
        parkingLot.park(new Object()); // spaceAvailable - -

        //assertFalse(parkingLot.park(new Object()));

        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(new Object());

        });
        assertEquals("parking lot is full", exception.getMessage());
    }

    @Test
    void givenParkingSameObject_WhenPark_ThenShouldNotPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);

        Object object = new Object();
        parkingLot.park(object);
        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> {
            parkingLot.park(object);

        });
        assertEquals("You cannot park same vehicle", exception.getMessage());
    }

    @Test
    void givenAlreadyParkedCar_WhenUnPark_thenShouldBeAbleToUnPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object alreadyParkedCar = new Object();
        parkingLot.park(alreadyParkedCar);

        assertTrue(parkingLot.unPark(alreadyParkedCar));
    }

    @Test
    void givenCarWhichIsNotParkedIn_WhenUnPark_thenShouldBeNotAbleToUnPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object CarWhichIsNotParkedIn = new Object();

        ParkingLotException exception = assertThrows(ParkingLotException.class, () -> {
            parkingLot.unPark(CarWhichIsNotParkedIn);
        });

        assertEquals("the car may not be parked here", exception.getMessage());
    }

}
