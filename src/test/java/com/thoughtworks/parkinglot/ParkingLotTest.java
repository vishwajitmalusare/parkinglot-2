package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.ParkingLotSameCarException;
import com.thoughtworks.ParkingLotVehicleNotParkException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() throws ParkingLotSameCarException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1);

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotPark() throws ParkingLotSameCarException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2);
       Object object = new Object();
        parkingLot.park(object);

        ParkingLotSameCarException exception = assertThrows(ParkingLotSameCarException.class, () -> {
            parkingLot.park(object);

        });
        assertEquals("You cannot park same vehicle", exception.getMessage());
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
    void givenAlreadyParkedCar_WhenUnPark_thenShouldBeAbleToUnPark() throws ParkingLotVehicleNotParkException, ParkingLotFullException, ParkingLotSameCarException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object alreadyParkedCar = new Object();
        parkingLot.park(alreadyParkedCar);

        assertEquals(alreadyParkedCar,parkingLot.unPark(alreadyParkedCar));
    }

    @Test
    void givenCarWhichIsNotParkedIn_WhenUnPark_thenShouldBeNotAbleToUnPark() throws ParkingLotSameCarException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object CarWhichIsNotParkedIn = new Object();
        Object CarWhichIsParked = new Object();
        parkingLot.park(CarWhichIsParked);

         ParkingLotVehicleNotParkException exception = assertThrows(ParkingLotVehicleNotParkException.class, () -> {
            parkingLot.unPark(CarWhichIsNotParkedIn);
        });

        assertEquals("Vehicle not parked, unable to un-park", exception.getMessage());
    }

    @Test
    void givenParkingLotIsEmpty_WhenUnPark_thenShouldNotBeAbleToUnPark() {
        ParkingLot parkingLot = new ParkingLot(0);
        Object vehicle = new Object();

        ParkingLotVehicleNotParkException exception = assertThrows(ParkingLotVehicleNotParkException.class, () -> {
            parkingLot.unPark(vehicle);
        });

        assertEquals("Vehicle not parked, unable to un-park", exception.getMessage());
    }
    @Test
    void givenParkingLotIsFull_WhenUnPark_thenShouldNotBeAbleToUnPark() {
        ParkingLot parkingLot = new ParkingLot(2);
        Object vehicleOne = new Object();
        Object vehicleTWo = new Object();
        Object vehicleThree = new Object();

        ParkingLotVehicleNotParkException exception = assertThrows(ParkingLotVehicleNotParkException.class, () -> {
            parkingLot.unPark(vehicleThree);
        });

        assertEquals("Vehicle not parked, unable to un-park", exception.getMessage());
    }
}
