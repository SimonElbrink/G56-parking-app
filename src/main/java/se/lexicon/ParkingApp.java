package se.lexicon;

import se.lexicon.dao.CustomerDao;
import se.lexicon.dao.ParkingSpotDao;
import se.lexicon.dao.ReservationDao;
import se.lexicon.dao.impl.CustomerDaoImpl;
import se.lexicon.dao.impl.ParkingSpotDaoImpl;
import se.lexicon.dao.impl.ReservationDaoImpl;
import se.lexicon.model.Customer;
import se.lexicon.model.ParkingSpot;
import se.lexicon.model.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ParkingApp {

    private ParkingSpotDao parkingSpotDao;
    private CustomerDao customerDao;
    private ReservationDao reservationDao;


    //The app is starting
    public ParkingApp() {
        parkingSpotDao = new ParkingSpotDaoImpl();
        customerDao = new CustomerDaoImpl();
        reservationDao = new ReservationDaoImpl();

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
        Scanner scanner = new Scanner(System.in);

        //Is there Available Spots?
        if (parkingSpotDao.findAvailableSpots().isEmpty()) {
            System.out.println(ConsoleColors.RED + "No Available parking spots." + ConsoleColors.RESET);
            return;
        }


        // What spot is selected?
        System.out.println("Enter Spot ID: ");
        String spotID = scanner.nextLine();

        Optional<ParkingSpot> foundSpot = parkingSpotDao.findBySpotNumber(Integer.parseInt(spotID));
        if (foundSpot.isEmpty()) {
            System.out.println(ConsoleColors.RED + "Invalid Spot ID" + ConsoleColors.RESET);
            return;
        }

        if (foundSpot.get().isOccupied()) {
            System.out.println(ConsoleColors.RED + "Spot is Occupied" + ConsoleColors.RESET);
            return;
        }

        // Do we have customer?
        System.out.println("Enter Customer ID: ");
        String customerID = scanner.nextLine();

        Optional<Customer> foundCustomer = customerDao.findById(Integer.parseInt(customerID));
        if (foundCustomer.isEmpty()) {
            System.out.println(ConsoleColors.RED + "Invalid Customer ID" + ConsoleColors.RESET);
            return;
        }

        // How long?
        System.out.println("Enter Reservation hours: ");
        String hours = scanner.nextLine();

        //Create Reservation
        Reservation reservation = new Reservation(foundSpot.get(), foundCustomer.get(), Integer.parseInt(hours));

        //Store the Reservation
        Reservation savedReservation = reservationDao.create(reservation);

        //Update occupy
        foundSpot.get().occupy();
        parkingSpotDao.update(foundSpot.get());


        //Display if it was successful
        System.out.println(ConsoleColors.GREEN + "Reservation Created: " + savedReservation + ConsoleColors.RESET);

    }

    private void displayParkingSpots() {

        if (parkingSpotDao.findAvailableSpots().isEmpty()) {
            System.out.println(ConsoleColors.RED + "No Available parking spots." + ConsoleColors.RESET);
            return;
        }

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

        Scanner scanner = new Scanner(System.in);
        // Request input for creation
        System.out.println("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.println("Enter vehicle plate number:");
        String plateNumber = scanner.nextLine();

        //Store customer
        Customer createdCustomer = customerDao.create(new Customer(name, phone, plateNumber));

        System.out.println(ConsoleColors.GREEN + "Customer registered with ID: " + createdCustomer.getId() + ConsoleColors.RESET);


    }


    private void initializeParkingSpots(int areaCode) {
        for (int i = 1; i <= 10; i++) {
            parkingSpotDao.create(new ParkingSpot(i, areaCode));
        }
    }


}
