package sweet.dh.studyhard.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sweet.dh.studyhard.entity.Customer;
import sweet.dh.studyhard.entity.Department;
import sweet.dh.studyhard.entity.User;
import sweet.dh.studyhard.entity.vo.CustomerAddress;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EntityManager entityManager;

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
        customer.addAddress("Los Angeles", "456 Elm St", "90001");
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

    @Test
    @DisplayName("clearAddresses() 메소드를 호출한 후 주소목록의 size가 0인지 검증하는 테스트")
    public void testClearAddresses() {
        //given
        Customer savedCustomer = customerService.saveCustomer(customer);

        //when
        savedCustomer.clearAddresses();

        //then
        assertEquals(0, getCountAddresses(savedCustomer));
    }

    private Long getCountAddresses(Customer savedCustomer) {
        entityManager.flush();
        entityManager.clear();
        String jpql = "SELECT COUNT(*) FROM Customer c JOIN c.addresses a WHERE c.id = :userId";
        TypedQuery<Long> query = entityManager.createQuery(jpql, Long.class);
        query.setParameter("userId", savedCustomer.getId());
        return query.getSingleResult();
    }


    @Test
    @DisplayName("Customer를 제거하면 자동으로 CustomerAddress도 제거되는지 검증하는 테스트")
    public void isDeletedAddressesIfDeleteCustomer() {
        Customer savedCustomer = customerService.saveCustomer(customer);
        customerService.deleteCustomer(savedCustomer.getId());

        Long count = getCountAddresses(savedCustomer);

        //deletedCustomer에 속해있던 주소목록의 size가 0인지 검증
        assertEquals(0, count);
    }

}