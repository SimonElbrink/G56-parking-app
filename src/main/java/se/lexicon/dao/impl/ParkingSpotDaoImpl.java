package se.lexicon.dao.impl;

import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.model.ParkingSpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingSpotDaoImpl implements ParkingSpotDao {

    private final List<ParkingSpot> parkingSpots = new ArrayList<>(); // In-memory storage for parking spots

    @Override
    public ParkingSpot create(ParkingSpot parkingSpot) {
        parkingSpots.add(parkingSpot);
        return parkingSpot;
    }

    @Override
    public List<ParkingSpot> findAll() {
        return new ArrayList<>(parkingSpots);
    }

    @Override
    public Optional<ParkingSpot> findBySpotNumber(Integer spotNumber) {
        for (ParkingSpot spot : parkingSpots) {
            if (spot.getSpotNumber().equals(spotNumber)) {
                return Optional.of(spot);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<ParkingSpot> findAvailableSpots() {
        // Filter for vacant spots
        List<ParkingSpot> list = new ArrayList<>();
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isOccupied()) {
                list.add(spot);
            }
        }
        return list;
    }

    @Override
    public void update(ParkingSpot spotToUpdate) {
        //Validate if data is valid

        if (spotToUpdate == null || spotToUpdate.getSpotNumber() == null){
            throw new IllegalArgumentException("Invalid parking spot: spotNumber cannot be null");
        }

        //Find it in storage
        Optional<ParkingSpot> foundSpot = findBySpotNumber(spotToUpdate.getSpotNumber());
        if (foundSpot.isPresent()){
            int index = parkingSpots.indexOf(foundSpot.get());

            //Replace it
            parkingSpots.set(index,spotToUpdate);
        }
    }

    @Override
    public boolean delete(Integer spotNumber) {
        if (spotNumber == null) {
            throw new IllegalArgumentException("Spot number cannot be null.");
        }

        for (ParkingSpot spot : parkingSpots) {
            if (spot.getSpotNumber().equals(spotNumber)) {
                parkingSpots.remove(spot);
                return true; // Successfully deleted
            }
        }

        return false; // Spot not found
    }
}
