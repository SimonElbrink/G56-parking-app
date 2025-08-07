package se.lexicon.dao;

import se.lexicon.model.Customer;

public interface CustomerDao {

    Customer create(Customer customer);

    Customer findById(int id);

    Customer finbdByPlateNumber();

//    List<Customer> findAll(); // Not alow to find all customers

}
