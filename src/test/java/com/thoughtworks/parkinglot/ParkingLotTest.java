package com.thoughtworks.parkinglot;

import com.thoughtworks.ParkingLotFullException;
import com.thoughtworks.ParkingLotSameCarException;
import com.thoughtworks.ParkingLotUnParkException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DummyOwner implements ParkingLotAuthority {
    boolean checked = false;
    boolean spaceAvilable = false;
    int isParkingLotFullCount = 0;
    int isSpaceAvailableCount = 0;

    public void isNotifyParkingLotFull() {
        checked = true;
        isParkingLotFullCount++;
    }

    public void isNotifySpaceAvailable() {
        spaceAvilable = true;
        isSpaceAvailableCount++;
    }

}

class SecurityGuard implements ParkingLotAuthority {
    boolean checked = false;
    boolean spaceAvilable = false;
    int isParkingLotFullCount = 0;
    int isSpaceAvailableCount = 0;

    public void isNotifyParkingLotFull() {
        checked = true;
        isParkingLotFullCount++;
    }

    public void isNotifySpaceAvailable() {
        spaceAvilable = true;
        isSpaceAvailableCount++;
    }

}

public class ParkingLotTest {

    @Test
    void givenParkingLotHasCapacity_WhenPark_ThenShouldPark() {
        List<ParkingLotAuthority> subscribers = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1, subscribers);
        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLotIsFull_WhenPark_ThenShouldNotPark() throws ParkingLotSameCarException, ParkingLotFullException {
        List<ParkingLotAuthority> subscribers = new ArrayList<>();

        ParkingLot parkingLot = new ParkingLot(2, subscribers);
        Object object = new Object();
        parkingLot.park(object);

        assertThrows(ParkingLotSameCarException.class, () -> {
            parkingLot.park(object);
        });
    }

    @Test
    void givenParkingSameObject_WhenPark_ThenShouldNotPark() throws ParkingLotSameCarException, ParkingLotFullException {
        List<ParkingLotAuthority> subscribers = new ArrayList<>();

        ParkingLot parkingLot = new ParkingLot(2, subscribers);

        Object object = new Object();
        parkingLot.park(object);
        assertThrows(ParkingLotSameCarException.class, () -> {
            parkingLot.park(object);
        });
    }


    @Nested
    class UnParkTest {
        @Test
        void givenParkingLotHasVehical_WhenUnparkAParkVehicle_thenShouldBeAbleToUnPark() throws ParkingLotUnParkException, ParkingLotFullException, ParkingLotSameCarException {
            List<ParkingLotAuthority> subscribers = new ArrayList<>();

            ParkingLot parkingLot = new ParkingLot(2, subscribers);
            Object vehicleOne = new Object();
            parkingLot.park(vehicleOne);
            assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));

        }

        @Test
        void givenParkingLotIsEmpty_WhenUnPark_thenShouldNotBeAbleToUnPark() {
            List<ParkingLotAuthority> subscribes = new ArrayList<>();
            ParkingLot parkingLot = new ParkingLot(0, subscribes);
            Object vehicle = new Object();

            assertThrows(ParkingLotUnParkException.class, () -> {
                parkingLot.unPark(vehicle);
            });

        }

        @Test
        void givenParkingLotIsFull_WhenUnPark_thenShouldNotBeAbleToUnPark() throws ParkingLotFullException, ParkingLotSameCarException {
            List<ParkingLotAuthority> subscribers = new ArrayList<>();
            ParkingLot parkingLot = new ParkingLot(2, subscribers);
            Object vehicleOne = new Object();
            Object vehicleTWo = new Object();
            Object vehicleThree = new Object();

            parkingLot.park(vehicleOne);
            parkingLot.park(vehicleTWo);
            assertThrows(ParkingLotUnParkException.class, () -> {
                parkingLot.unPark(vehicleThree);
            });

        }

        @Test
        void givenParkingLotIsFull_whenNotify_thenShouldNotifyOwner() throws ParkingLotFullException, ParkingLotSameCarException, ParkingLotUnParkException {
            List<ParkingLotAuthority> subscribes = new ArrayList<>();
            DummyOwner owner = new DummyOwner();
            subscribes.add(owner);
            ParkingLot parkingLot = new ParkingLot(2, subscribes);
            Object vehicleOne = new Object();
            Object vehicleTwo = new Object();
            Object vehicleThree = new Object();

            parkingLot.park(vehicleOne);
            parkingLot.park(vehicleTwo);
            parkingLot.unPark(vehicleOne);
            parkingLot.park(vehicleThree);

            assertEquals(2, owner.isParkingLotFullCount);
        }

        @Test
        void givenParkingLotHasAvailableSpaceWhenNotifyThenShouldNotifySpaceAvailable() throws ParkingLotFullException, ParkingLotSameCarException, ParkingLotUnParkException {
            List<ParkingLotAuthority> subscribers = new ArrayList<>();
            DummyOwner owner =new DummyOwner();
            subscribers.add(owner);

            ParkingLot parkingLot = new ParkingLot(4, subscribers);
            Object vehicleOne = new Object();
            Object vehicleTwo = new Object();
            Object vehicleThree = new Object();
            Object vehicleFour = new Object();
            Object vehicleFive = new Object();

            parkingLot.park(vehicleOne);
            parkingLot.park(vehicleTwo);
            parkingLot.park(vehicleThree);
            parkingLot.park(vehicleFour);

            parkingLot.unPark(vehicleFour);

            parkingLot.park(vehicleFive);

            parkingLot.unPark(vehicleTwo);
            parkingLot.unPark(vehicleThree);


            assertEquals(2, owner.isParkingLotFullCount);
            assertEquals(2, owner.isSpaceAvailableCount);
        }

        @Test
        void givenParkingLotIsFullOrSpaceAvailable_WhenNotify_ThenShouldNotifySecurityGuard() throws ParkingLotFullException, ParkingLotSameCarException, ParkingLotUnParkException {
            List<ParkingLotAuthority> subscribers = new ArrayList<>();
            SecurityGuard securityGuard =new SecurityGuard();
            subscribers.add(securityGuard);
            ParkingLot parkingLot = new ParkingLot(3, subscribers);
            Object vehicleOne = new Object();
            Object vehicleTwo = new Object();
            Object vehicleThree = new Object();

            parkingLot.park(vehicleOne);
            parkingLot.park(vehicleTwo);
            parkingLot.park(vehicleThree);

            parkingLot.unPark(vehicleTwo);

            parkingLot.park(vehicleTwo);

            assertEquals(2, securityGuard.isParkingLotFullCount);
            assertEquals(1, securityGuard.isSpaceAvailableCount);
        }

        @Test
        void givenParkingLotFullOrSpaceAvailable_WhenNotify_ThenShouldNotifyAllSubscriber() throws ParkingLotFullException, ParkingLotSameCarException, ParkingLotUnParkException {
            List<ParkingLotAuthority> subscribers = new ArrayList<>();
            DummyOwner owner = new DummyOwner();
            SecurityGuard securityGuardOne = new SecurityGuard();

            subscribers.add(owner);
            subscribers.add(securityGuardOne);
            ParkingLot parkingLot = new ParkingLot(2, subscribers);

            Object vehicleOne = new Object();
            Object vehicleTwo = new Object();

            parkingLot.park(vehicleOne);
            parkingLot.park(vehicleTwo);
            parkingLot.unPark(vehicleOne);
            parkingLot.park(vehicleOne);

            assertEquals(2, securityGuardOne.isParkingLotFullCount);
            assertEquals(1, securityGuardOne.isSpaceAvailableCount);

            assertEquals(2, owner.isParkingLotFullCount);
            assertEquals(1, owner.isSpaceAvailableCount);
        }

        @Test
        void givenParkingLotFullOrSpaceAvailable_WhenNotify_ThenShouldNotifyToOnlySubscribedUsers() throws ParkingLotFullException, ParkingLotSameCarException, ParkingLotUnParkException {
            List<ParkingLotAuthority> subscribers = new ArrayList<>();
            DummyOwner owner = new DummyOwner();
            SecurityGuard securityGuardOne = new SecurityGuard();
            SecurityGuard securityGuardTwo = new SecurityGuard();

            subscribers.add(owner);
            subscribers.add(securityGuardOne);
            subscribers.add(securityGuardTwo);
            subscribers.remove(securityGuardTwo);
            ParkingLot parkingLot = new ParkingLot(2, subscribers);

            Object vehicleOne = new Object();
            Object vehicleTwo = new Object();

            parkingLot.park(vehicleOne);
            parkingLot.park(vehicleTwo);
            parkingLot.unPark(vehicleOne);
            parkingLot.park(vehicleOne);

            assertEquals(2, securityGuardOne.isParkingLotFullCount);
            assertEquals(1, securityGuardOne.isSpaceAvailableCount);

            assertEquals(2, owner.isParkingLotFullCount);
            assertEquals(1, owner.isSpaceAvailableCount);

            assertEquals(0,securityGuardTwo.isSpaceAvailableCount);
            assertEquals(0,securityGuardTwo.isParkingLotFullCount);
        }

        @Test
        void givenParkingLotFullOrSpaceAvailable_WhenNotify_ThenShouldNotifyToOnlySubscribedUsersMutiple() throws ParkingLotFullException, ParkingLotSameCarException, ParkingLotUnParkException {
            List<ParkingLotAuthority> subscribers = new ArrayList<>();
            DummyOwner owner = new DummyOwner();
            SecurityGuard securityGuardOne = new SecurityGuard();
            SecurityGuard securityGuardTwo = new SecurityGuard();
            SecurityGuard securityGuardThree = new SecurityGuard();

            subscribers.add(owner);
            subscribers.add(securityGuardOne);
            subscribers.add(securityGuardTwo);
            subscribers.remove(securityGuardTwo);
            subscribers.add(securityGuardThree);

            ParkingLot parkingLot = new ParkingLot(2, subscribers);

            Object vehicleOne = new Object();
            Object vehicleTwo = new Object();

            parkingLot.park(vehicleOne);
            parkingLot.park(vehicleTwo);
            parkingLot.unPark(vehicleOne);
            parkingLot.park(vehicleOne);

            assertEquals(2, securityGuardOne.isParkingLotFullCount);
            assertEquals(1, securityGuardOne.isSpaceAvailableCount);

            assertEquals(2, owner.isParkingLotFullCount);
            assertEquals(1, owner.isSpaceAvailableCount);

            assertEquals(0,securityGuardTwo.isSpaceAvailableCount);
            assertEquals(0,securityGuardTwo.isParkingLotFullCount);

            assertEquals(2, securityGuardThree.isParkingLotFullCount);
            assertEquals(1, securityGuardThree.isSpaceAvailableCount);
        }
    }
}
