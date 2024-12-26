package sweet.dh.studyhard.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweet.dh.studyhard.order.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}