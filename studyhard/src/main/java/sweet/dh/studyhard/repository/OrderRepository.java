package sweet.dh.studyhard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweet.dh.studyhard.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}