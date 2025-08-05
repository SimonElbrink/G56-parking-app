package se.lexicon.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a reservation in the parking management system.
 */
public class Reservation {
    //Fields
    private final String reservationId;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private final ParkingSpot parkingSpot; //Many-To-One
    private final Customer customer; // Many-to-One

    //Constructor
    public Reservation(ParkingSpot parkingSpot, Customer customer, int hours) {
        this.reservationId = UUID.randomUUID().toString();
        this.startTime = LocalDateTime.now();
        this.endTime = startTime.plusHours(hours);
        this.status = Status.ACTIVE;
        this.parkingSpot = Objects.requireNonNull(parkingSpot, "Parking spot cannot be null.");
        this.customer = Objects.requireNonNull(customer, "Customer cannot be null.");
    }

    //Methods (Getters/Setters/equals&hashCode/toString)
    public void complete() {
        this.status = Status.COMPLETED;
    }

    public void setEndTimeByHours(int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("Hours must be greater the 0");
        }
        this.endTime = this.startTime.plusHours(hours);
    }


    public String getReservationId() {
        return reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return """
                Reservation Details:
                ----------------------
                Reservation ID : %s
                Start Time     : %s
                End Time       : %s
                Parking Spot   : %s
                Customer       : %s
                ----------------------
                """.formatted(
                reservationId,
                startTime.truncatedTo(ChronoUnit.MINUTES).format(DateTimeFormatter.ofPattern("yyyy MMMM dd - hh:mm")),
                endTime.truncatedTo(ChronoUnit.MINUTES).format(DateTimeFormatter.ofPattern("yyyy MMMM dd - hh:mm")),
                parkingSpot,
                customer
        );
    }
}
