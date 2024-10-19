package sweet.dh.studyhard.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sweet.dh.studyhard.entity.Customer;
import sweet.dh.studyhard.entity.vo.CustomerAddress;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customerService.deleteAllCustomers();
        customer = Customer.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .phone("123-456-7890")
                .build();

        customer.addAddress("New York", "123 Main St", "10001");
    }

    @Test
    public void testSaveCustomer() {
        Customer savedCustomer = customerService.saveCustomer(customer);
        assertNotNull(savedCustomer.getId());
    }

    @Test
    public void testGetAllCustomers() {
        customerService.saveCustomer(customer);
        List<Customer> customers = customerService.getAllCustomers();
        assertFalse(customers.isEmpty());
    }

    @Test
    public void testGetCustomerById() {
        Customer savedCustomer = customerService.saveCustomer(customer);
        Customer foundCustomer = customerService.getCustomerById(savedCustomer.getId());
        assertNotNull(foundCustomer);
    }

    @Test
    public void testDeleteCustomer() {
        Customer savedCustomer = customerService.saveCustomer(customer);
        customerService.deleteCustomer(savedCustomer.getId());
        Customer deletedCustomer = customerService.getCustomerById(savedCustomer.getId());
        assertNull(deletedCustomer);
    }


}