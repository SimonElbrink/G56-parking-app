package se.lexicon.model;

/**
 * Represents a parking spot within a parking lot.
 */
public class ParkingSpot {

    //Fields
    private Integer spotNumber;
    private Integer areaCode;
    private boolean occupied;

    /**
     * Constructor to initialize a parking spot with a spot number and area code.
     * Defaults: occupied = false.
     *
     * @param spotNumber The unique spot number.
     * @param areaCode   The area code where the parking spot is located.
     */
    public ParkingSpot(Integer spotNumber, Integer areaCode) {
        this.spotNumber = spotNumber;
        this.areaCode = areaCode;
    }


    /**
     * Constructor to initialize a parking spot with all attributes
     *
     * @param spotNumber The unique spot Number.
     * @param areaCode   The area code where the parking spot is located.
     * @param occupied   The occupancy status of the parking spot.
     */
    public ParkingSpot(Integer spotNumber, Integer areaCode, boolean occupied) {
        this(spotNumber,areaCode);
        this.occupied = occupied;
    }

    //Methods (Getters/Setters/equals&hashCode/toString)

    public Integer getSpotNumber() {
        return spotNumber;
    }

    public Integer getAreaCode() {
        return areaCode;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setSpotNumber(Integer spotNumber) {
        //Todo: Validate Input
        this.spotNumber = spotNumber;
    }

    public void setAreaCode(Integer areaCode) {
        //Todo: Validate Input
        this.areaCode = areaCode;
    }

/*
    protected void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
*/

    public void occupy(){
        this.occupied = true;
    }
    public void vacate(){
        this.occupied = false;
    }


    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotNumber=" + spotNumber +
                ", areaCode=" + areaCode +
                ", occupied=" + occupied +
                '}';
    }
}
