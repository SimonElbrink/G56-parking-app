package se.lexicon;

import se.lexicon.model.Customer;

public class Main {
    public static void main(String[] args) {

        // Instance of a customer =  instantiation of an Object
        // Default Values
        Customer customer = new Customer("Simon", "0123456789", "ABC123");

        //Update Values
//        customer.setId(null);
        customer.setVehiclePlateNumber("CBA321"); // Update

        customer.setPhoneNumber("Simon@lexion.se");

        System.out.println(customer);

    }
}
