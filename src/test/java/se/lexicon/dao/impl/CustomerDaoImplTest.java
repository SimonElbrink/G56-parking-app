package se.lexicon.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Customer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoImplTest {

    private CustomerDaoImpl customerDao;

    @BeforeEach
    void setUp(){
        customerDao = new CustomerDaoImpl();
    }


    @Test
    @DisplayName("Should create customer successfully and assign ID")
    void createCustomerSuccessfully() {
        // Scenario: Creating a new Customer
        Customer customer = new Customer("Test Name", "0123456789", "ABC123");

        // Expected: a new Customer should be assigned with a unique ID.
        Customer createdCustomer = customerDao.create(customer);

        // Actual: Validate that the customer id is created
        // Verify: Check that ID is not Null, and the customer data matches except ID
        assertNotNull(createdCustomer.getId(), "Customer ID should not be null");
        assertEquals(customer.getName(), createdCustomer.getName());
        assertEquals(customer.getPhoneNumber(), createdCustomer.getPhoneNumber());
        assertEquals(customer.getVehiclePlateNumber(), createdCustomer.getVehiclePlateNumber());
    }

    @Test
    @DisplayName("Should find a customer by ID when the customer exists")
    void findByIdReturnsCustomerWhenExists() {
        // Scenario: Add a customer and search by its ID
        Customer customer = new Customer("Test Name", "0123456789", "ABC123");
        Customer createdCustomer = customerDao.create(customer);

        // Expected: The customer should be found using the generated ID
        Optional<Customer> foundCustomer = customerDao.findById(createdCustomer.getId());

        // Actual: Retrieve customer using findById method
        // Verify: Ensure the customer is present and matches the created customer
        assertTrue(foundCustomer.isPresent(), "Customer should be found");
        assertEquals(createdCustomer, foundCustomer.get(), "Found customer should match the created customer");
    }

    @Test
    @DisplayName("Should return empty when no customer exists for the given ID")
    void findByIdReturnsEmptyWhenNotExists() {
        // Scenario: Use non-existing customer ID
        int nonExistentId = 999;

        // Expected: Optional empty is returned
        Optional<Customer> foundCustomer = customerDao.findById(nonExistentId);

        // Actual & Verify: Optional should be empty
        assertFalse(foundCustomer.isPresent(), "Customer should not be found");
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for invalid phone number")
    void saveCustomerWithInvalidPhoneNumberThrowsException() {
        // Scenario & Expected: Invalid phone number should throw exception on customer creation
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("Test Name", "invalidPhone", "ABC123");
        });

        // Verify: Exception message contains indication of invalid phone number
        assertTrue(exception.getMessage().contains("Invalid phoneNumber"), "Exception message should indicate invalid phone number");
    }


}