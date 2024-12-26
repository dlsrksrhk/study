package sweet.dh.studyhard.order.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sweet.dh.studyhard.order.entity.Customer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void 고객_저장_테스트1() {
        customerRepository.save(new Customer("John", "Doe", "02-111"));
        List<Customer> customers = customerRepository.findAll();
        // then
        assertThat(customers.size()).isEqualTo(1);
        assertThat(customers.get(0).getName()).isEqualTo("John");
    }

    @Test
    public void 고객_저장_테스트2() {
        customerRepository.save(new Customer("John2", "Doe2", "02-222"));
        List<Customer> customers = customerRepository.findAll();
        // then
        assertThat(customers.size()).isEqualTo(1);
        assertThat(customers.get(0).getName()).isEqualTo("John2");
    }
}