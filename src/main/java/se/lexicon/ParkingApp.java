package se.lexicon;

import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.dao.impl.ParkingSpotDaoImpl;
import se.lexicon.model.ParkingSpot;

import java.util.List;
import java.util.Scanner;

public class ParkingApp {

    private ParkingSpotDao parkingSpotDao;


    //The app is starting
    public ParkingApp() {
        parkingSpotDao = new ParkingSpotDaoImpl();

        initializeParkingSpots(50005);
    }

    public void runApp() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello. Welcome To Parking App 👋🏼");

        while (running) {
            System.out.println("""
                    1. Register Customer
                    2. Display Parking Spots
                    3. Reserve a Parking Spot
                    4. Vacate Parking Spot
                    5. Exit
                    """
            );

            System.out.println("Choose and option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> registerCustomer();
                case "2" -> displayParkingSpots();
                case "3" -> reserveParkingSpot();
                case "4" -> vacateParkingSpot();
                case "5" -> {
                    System.out.println("Exiting... Goodbye. 👋🏼");
                    running = false;
                }
                default -> System.out.println("Invalid option, try again");
            }
        }

    }


    private void vacateParkingSpot() {
    }

    private void reserveParkingSpot() {
    }

    private void displayParkingSpots() {

        List<ParkingSpot> spots = parkingSpotDao.findAll();

        System.out.println("==== Parking Spots ====");
        for (ParkingSpot spot : spots) {

            String status = spot.isOccupied() ? "X" : "✓";
/*
            if (spot.isOccupied()){
                status = "X";
            }else{
                status = "✓";
            }
*/
            System.out.println("Spot ID: " + spot.getSpotNumber() + " | Area: " + spot.getAreaCode() + " | Status: " + status);
        }

    }

    private void registerCustomer() {

    }


    private void initializeParkingSpots(int areaCode) {
        for (int i = 1; i <= 10; i++) {
            parkingSpotDao.create(new ParkingSpot(i, areaCode));
        }
    }


}
