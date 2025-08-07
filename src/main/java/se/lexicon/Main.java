package se.lexicon;

import se.lexicon.dao.CustomerDao;
import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.dao.impl.CustomerDaoImpl;
import se.lexicon.dao.impl.ParkingSpotDaoImpl;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        CustomerDao customerStorage = new CustomerDaoImpl();
        ParkingSpotDao parkingSpotDao = new ParkingSpotDaoImpl();

        Customer simon = new Customer("Simon", "0123456789", "ABC123");
        System.out.println(simon);

        customerStorage.create(simon);

        System.out.println(simon);

        Optional<Customer> findById = customerStorage.findById(1);

        System.out.println(findById.get().getName());

        ParkingSpot parkingSpot = new ParkingSpot(20, 20005);
        parkingSpotDao.create(parkingSpot);

        parkingSpot.setAreaCode(50004);
        parkingSpotDao.update(parkingSpot);

    }
}
