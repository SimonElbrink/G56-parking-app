package se.lexicon.dao.impl;

import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.model.ParkingSpot;

import java.util.List;

public class ParkingSpotDaoImpl implements ParkingSpotDao {

    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
        //TODO: Implement
        return null;
    }

    @Override
    public List<ParkingSpot> findAll() {
        //TODO: Implement
        return List.of();
    }

    @Override
    public ParkingSpot findBySpotNumber(Integer spotNumber) {
        //TODO: Implement
        return null;
    }

    @Override
    public List<ParkingSpot> findAvailableSpots() {
        //TODO: Implement
        return List.of();
    }

    @Override
    public void update(ParkingSpot parkingSpot) {
        //TODO: Implement

    }

    @Override
    public boolean delete(Integer spotNumber) {
        //TODO: Implement
        return false;
    }
}
