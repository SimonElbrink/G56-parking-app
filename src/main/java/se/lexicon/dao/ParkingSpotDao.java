package se.lexicon.dao;

import se.lexicon.model.ParkingSpot;

import java.util.List;

public interface ParkingSpotDao {

    //CRUD - Create, Read, Update, Delete

    ParkingSpot create(ParkingSpot parkingSpot);

    List<ParkingSpot> findAll();

    ParkingSpot findBySpotNumber(Integer spotNumber);

    List<ParkingSpot> findAvailableSpots();

    void update(ParkingSpot parkingSpot);

    boolean delete(Integer spotNumber);


}
