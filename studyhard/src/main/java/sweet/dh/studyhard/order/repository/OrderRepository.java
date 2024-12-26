package sweet.dh.studyhard.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sweet.dh.studyhard.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}