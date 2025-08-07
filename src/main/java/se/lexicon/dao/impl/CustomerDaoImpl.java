package se.lexicon.dao.impl;

import se.lexicon.dao.CustomerDao;
import se.lexicon.dao.sequencer.CustomerIdSequencer;
import se.lexicon.model.Customer;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {

    //Data storage
    List<Customer> customersList = new ArrayList<>();

    @Override
    public Customer create(Customer customer) {
        int generatedId = CustomerIdSequencer.nextId();
        customer.setId(generatedId);
        customersList.add(customer);

        return customer;
    }

    //https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html
    @Override
    public Optional<Customer> findById(int id) {
        for (Customer customer: customersList){
            if (customer.getId() == id){
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }

    @Override
    public Customer findByPlateNumber() {
        //TODO: Implement
        return null;
    }
}
