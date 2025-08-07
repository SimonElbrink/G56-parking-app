package se.lexicon.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.model.ParkingSpot;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class ParkingSpotDaoImplTest {

    private ParkingSpotDaoImpl parkingSpotDao;

    @BeforeEach
    void setUp() {
        parkingSpotDao = new ParkingSpotDaoImpl();
    }

    @Test
    @DisplayName("Creating duplicate parking spots allows duplicates")
    void testCreateDuplicateParkingSpots() {
        // Scenario: Creating two parking spots with same spot number
        ParkingSpot spot1 = new ParkingSpot(101, 1, false);
        ParkingSpot spot2 = new ParkingSpot(101, 1, true);

        // Expected: Both spots should be stored successfully
        parkingSpotDao.create(spot1);
        parkingSpotDao.create(spot2);

        // Actual: Retrieve all spots
        List<ParkingSpot> spots = parkingSpotDao.findAll();

        // Verify: Both spots should be present in the list
        assertEquals(2, spots.size());
        assertTrue(spots.contains(spot1));
        assertTrue(spots.contains(spot2));
    }

    @Test
    @DisplayName("Update non-existing parking spot does not add it")
    void testUpdateNonExistingSpotDoesNotAdd() {
        // Scenario: Attempting to update a non-existing parking spot
        ParkingSpot spot = new ParkingSpot(500, 5, false);

        // Expected: Update should not create new spot
        parkingSpotDao.update(spot);

        // Actual: Try to find the spot
        Optional<ParkingSpot> found = parkingSpotDao.findBySpotNumber(500);

        // Verify: Spot should not exist
        assertFalse(found.isPresent(), "Updating non-existing spot should not add it");
    }

    @Test
    @DisplayName("Modifying returned list from findAll does not affect internal DAO state")
    void testFindAllReturnsDefensiveCopy() {
        // Scenario: Create a spot and modify the returned list
        ParkingSpot spot = new ParkingSpot(600, 6, true);
        parkingSpotDao.create(spot);

        // Expected: Modifying returned list should not affect DAO
        List<ParkingSpot> allSpots = parkingSpotDao.findAll();
        int initialSize = allSpots.size();
        allSpots.clear();

        // Actual: Get spots list again
        List<ParkingSpot> spotsAfterClear = parkingSpotDao.findAll();

        // Verify: Original data should remain unchanged
        assertEquals(initialSize, spotsAfterClear.size(), "Clearing external list should not affect DAO internal state");
    }

    @Test
    @DisplayName("Modifying returned list from findAvailableSpots does not affect internal DAO state")
    void testFindAvailableSpotsReturnsDefensiveCopy() {
        // Scenario: Create spots and modify returned available spots list
        parkingSpotDao.create(new ParkingSpot(601, 6, false));
        parkingSpotDao.create(new ParkingSpot(602, 6, true));

        // Expected: Modifying returned list should not affect DAO
        List<ParkingSpot> availableSpots = parkingSpotDao.findAvailableSpots();
        int sizeBefore = availableSpots.size();
        availableSpots.clear();

        // Actual: Get available spots again
        List<ParkingSpot> availableSpotsAfter = parkingSpotDao.findAvailableSpots();

        // Verify: Original data should remain unchanged
        assertEquals(sizeBefore, availableSpotsAfter.size(), "Modifying returned available spots list should not affect DAO internal state");
    }

    @Test
    @DisplayName("Create method throws IllegalArgumentException if given null")
    void testCreateNullThrowsException() {
        // Scenario: Attempt to create null parking spot

        // Expected: NullPointerException should be thrown

        // Actual & Verify: Check exception is thrown
        assertThrows(IllegalArgumentException.class, () -> parkingSpotDao.create(null));
    }

    @Test
    @DisplayName("Find by spot number with null returns empty")
    void testFindBySpotNumberWithNull() {
        // Scenario: Search for parking spot with null spot number

        // Expected: Should return empty Optional

        // Actual: Call findBySpotNumber with null
        Optional<ParkingSpot> result = parkingSpotDao.findBySpotNumber(null);

        // Verify: Result should be empty
        assertFalse(result.isPresent(), "findBySpotNumber(null) should return empty Optional");
    }

    @Test
    @DisplayName("findAll returns empty list if DAO empty")
    void testFindAllReturnsEmptyWhenNoSpots() {
        // Scenario: Empty parking spot database

        // Expected: findAll should return empty list

        // Actual: Call findAll
        List<ParkingSpot> spots = parkingSpotDao.findAll();

        // Verify: List should be empty
        assertTrue(spots.isEmpty(), "findAll should return empty list when no spots");
    }

    @Test
    @DisplayName("findAvailableSpots returns empty list if DAO empty")
    void testFindAvailableSpotsReturnsEmptyWhenNoSpots() {
        // Scenario: Empty parking spot database

        // Expected: findAvailableSpots should return empty list

        // Actual: Call findAvailableSpots
        List<ParkingSpot> spots = parkingSpotDao.findAvailableSpots();

        // Verify: List should be empty
        assertTrue(spots.isEmpty(), "findAvailableSpots should return empty list when no spots");
    }

}