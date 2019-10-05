package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.ParkingLotSameCarException;
import com.thoughtworks.ParkingLotVehicleNotParkException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    class DummyOwner extends Owner {
        boolean checked = false;
        int count;

        public void isNotify(){
            if (checked = true){
                count ++;
            }
        }

    }
    DummyOwner owner = new DummyOwner();

    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() throws ParkingLotSameCarException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(1,owner);

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotPark() throws ParkingLotSameCarException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2,owner);
        Object object = new Object();
        parkingLot.park(object);

        ParkingLotSameCarException exception = assertThrows(ParkingLotSameCarException.class, () -> {
            parkingLot.park(object);

        });
        assertEquals("You cannot park same vehicle", exception.getMessage());
    }

    @Test
    void givenParkingSameObject_WhenPark_ThenShouldNotPark() throws ParkingLotSameCarException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2,owner);

        Object object = new Object();
        parkingLot.park(object);
        ParkingLotSameCarException exception = assertThrows(ParkingLotSameCarException.class, () -> {
            parkingLot.park(object);

        });
        assertEquals("You cannot park same vehicle", exception.getMessage());
    }


    @Nested
    class UnParkTest {
        @Test
        void givenParkingLotHasVehical_WhenUnparkAParkVehicle_thenShouldBeAbleToUnPark() throws ParkingLotVehicleNotParkException, ParkingLotFullException, ParkingLotSameCarException {
            ParkingLot parkingLot = new ParkingLot(2,owner);
            Object vehicleOne = new Object();
            parkingLot.park(vehicleOne);
            assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));

        }

        @Test
        void givenParkingLotIsEmpty_WhenUnPark_thenShouldNotBeAbleToUnPark() {
            ParkingLot parkingLot = new ParkingLot(0,owner);
            Object vehicle = new Object();

            ParkingLotVehicleNotParkException exception = assertThrows(ParkingLotVehicleNotParkException.class, () -> {
                parkingLot.unPark(vehicle);
            });

            assertEquals("Vehicle not parked, unable to un-park", exception.getMessage());
        }

        @Test
        void givenParkingLotIsFull_WhenUnPark_thenShouldNotBeAbleToUnPark() throws ParkingLotFullException, ParkingLotSameCarException {
            ParkingLot parkingLot = new ParkingLot(2,owner);
            Object vehicleOne = new Object();
            Object vehicleTWo = new Object();
            Object vehicleThree = new Object();

            parkingLot.park(vehicleOne);
            parkingLot.park(vehicleTWo);
            ParkingLotVehicleNotParkException exception = assertThrows(ParkingLotVehicleNotParkException.class, () -> {
                parkingLot.unPark(vehicleThree);
            });

            assertEquals("Vehicle not parked, unable to un-park", exception.getMessage());
        }

        @Test
        void givenParkingLotIsFull_whenNotify_thenShouldNotifyOwner() throws ParkingLotFullException, ParkingLotSameCarException {
            DummyOwner owner = new DummyOwner();
            ParkingLot parkingLot = new ParkingLot(2,owner);
            Object vehicleOne = new Object();
            Object vehicleTwo = new Object();

            parkingLot.park(vehicleOne);
            parkingLot.park(vehicleTwo);

            assertTrue(owner.checked);
        }
    }

}
