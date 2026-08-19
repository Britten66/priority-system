package ca.chrisbritten.warehouse.repository;

import ca.chrisbritten.warehouse.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
