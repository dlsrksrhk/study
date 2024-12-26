package sweet.dh.studyhard.order.service;

import sweet.dh.studyhard.order.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    void deleteCustomer(Long id);
    void deleteAllCustomers();
}