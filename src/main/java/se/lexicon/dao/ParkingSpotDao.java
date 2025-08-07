package se.lexicon.dao;

import se.lexicon.model.ParkingSpot;

import java.util.List;
import java.util.Optional;

public interface ParkingSpotDao {

    //CRUD - Create, Read, Update, Delete

    ParkingSpot create(ParkingSpot parkingSpot);

    List<ParkingSpot> findAll();

    Optional<ParkingSpot> findBySpotNumber(Integer spotNumber);

    List<ParkingSpot> findAvailableSpots();

    void update(ParkingSpot parkingSpot);

    boolean delete(Integer spotNumber);


}
