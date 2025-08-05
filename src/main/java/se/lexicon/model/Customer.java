package se.lexicon.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a customer with its information and vehicles plate number.
 */
public class Customer {

    //Fields
    private Integer id; // null
    private String name; // null
    private String phoneNumber; // null
    private String vehiclePlateNumber; // null

    //Constructor ?

    public Customer(String name, String phoneNumber, String vehiclePlateNumber) {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
        this.setVehiclePlateNumber(vehiclePlateNumber);
    }


    //Methods (Getters/Setters/equals&hashCode/toString)


    public void setId(Integer id) {
//        if (id == null) throw new IllegalArgumentException("ID can not be null!");
        Objects.requireNonNull(id, "Id should not be null");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name should ot be null or empty!");
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {

        if (phoneNumber == null || phoneNumber.isEmpty())
            throw new IllegalArgumentException("PhoneNumber should not be null or empty.");


        String phoneRegex = "^\\+?[0-9]{10,15}$";
        /*
            ^ → Start of the string.
            \\+? → Allows an optional + at the beginning (for international numbers).
            [0-9]{10,15} → Ensures the phone number consists of 10 to 15 digits.
            $ → End of the string.
         */
        Pattern PHONE_PATTERN = Pattern.compile(phoneRegex);
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        boolean isMatching = matcher.matches();

        if (!isMatching) throw new IllegalArgumentException("Invalid phoneNumber");

        this.phoneNumber = phoneNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        Objects.requireNonNull(vehiclePlateNumber, "vehiclePlateNumber should not be null");
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", vehiclePlateNumber='" + vehiclePlateNumber + '\'' +
                '}';
    }
}
