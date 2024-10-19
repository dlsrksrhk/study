package sweet.dh.studyhard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweet.dh.studyhard.entity.Customer;
import sweet.dh.studyhard.entity.User;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}