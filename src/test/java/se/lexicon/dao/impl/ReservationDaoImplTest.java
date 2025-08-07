package se.lexicon.dao.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;
import se.lexicon.model.Status;

import static org.junit.jupiter.api.Assertions.*;


class ReservationDaoImplTest {

    @Test
    @DisplayName("Should create reservation successfully with valid fields")
    void createReservationSuccessfully() {
        // Scenario: Creating a new Reservation
        ParkingSpot parkingSpot = new ParkingSpot(1, 100, false); // updated constructor call with spotNumber, areaCode, occupied
        Customer customer = new Customer("Test Customer", "0123456789", "ABC123");
        int hours = 2;

        Reservation reservation = new Reservation(parkingSpot, customer, hours);

        // Expected and Actual
        assertNotNull(reservation.getReservationId(), "Reservation ID should not be null");
        assertEquals(parkingSpot, reservation.getParkingSpot(), "ParkingSpot should match");
        assertEquals(customer, reservation.getCustomer(), "Customer should match");
        assertEquals(Status.ACTIVE, reservation.getStatus(), "Status should be ACTIVE");
        assertNotNull(reservation.getStartTime(), "Start time should not be null");
        assertNotNull(reservation.getEndTime(), "End time should not be null");
        assertTrue(reservation.getEndTime().isAfter(reservation.getStartTime()), "End time should be after start time");
    }


    @Test
    @DisplayName("Should complete reservation and change status")
    @Disabled("Not yet implemented")
    void completeReservationChangesStatus() throws InterruptedException {

    }

    @Test
    @DisplayName("Should set end time properly by adjusting hours")
    @Disabled("Not yet implemented")
    void setEndTimeByHoursAdjustsEndTime() {

    }
}