package ca.chrisbritten.warehouse.repository;

import ca.chrisbritten.warehouse.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
