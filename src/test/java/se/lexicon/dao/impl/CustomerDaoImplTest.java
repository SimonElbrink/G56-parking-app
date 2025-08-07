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
    void testCreateCustomerSuccessfully(){
        //Scenario: Creating a new Customer
        Customer customer = new Customer("Test Name", "0123456789", "ABC123");

        //Expected: a new Customer should be assigned with a unique ID.
        Customer createdCustomer = customerDao.create(customer);

        //Actual: Validate that the customer id is created
        //Verify: Check that ID is not Null, and the customer is not modified beyond id.
        assertNotNull(createdCustomer.getId(), "Customer ID should not be null");
        assertEquals(customer, createdCustomer);
    }

    @Test
    @DisplayName("Should find a customer by ID when the customer exists")
    void testFindByCustomerIDReturnsWhenExists() {
        //Scenario - Arrange
        Customer customer = new Customer("Test Name", "0123456789", "ABC123");
        customerDao.create(customer);

        //Expected - Act
        Optional<Customer> found = customerDao.findById(customer.getId());

        //Verify - Assert
        assertTrue(found.isPresent());
        assertEquals(customer,found.get());

    }

    @Test
    @DisplayName("Should return empty when no customer exists for the given ID")
    void findCustomerByIdReturnsEmptyWhenNotExists() {
        // Scenario
        int nonExistentId = 999;

        // Expected
        Optional<Customer> foundOptionalCustomer = customerDao.findById(nonExistentId); // 999

        // Verify
        assertFalse(foundOptionalCustomer.isPresent());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for invalid phone number")
    void saveCustomerWithInvalidPhoneNumberThrowsException() {
        // todo: needs completion :)
    }
}