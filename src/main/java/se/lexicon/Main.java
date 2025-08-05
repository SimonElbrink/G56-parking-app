package se.lexicon;

import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;

public class Main {
    public static void main(String[] args) {

        // Instance of a customer =  instantiation of an Object
        // Default Values
        Customer customer = new Customer("Simon", "0123456789", "ABC123");

        //Update Values
//        customer.setId(null);
        customer.setVehiclePlateNumber("CBA321"); // Update
//        customer.setPhoneNumber("Simon@lexion.se"); //Not allowed

        System.out.println(customer);

        ParkingSpot spot1 = new ParkingSpot(1, 10003);
        ParkingSpot spot2 = new ParkingSpot(2, 10003);
        spot2.occupy();
        new ParkingSpot(3,10003,false);

        System.out.println(spot1);
        System.out.println(spot2);


        Reservation reservation = new Reservation(spot1, customer, 2);

        reservation.setEndTimeByHours(5);
        reservation.complete();

        System.out.println(reservation);

    }
}
