package ca.chrisbritten.warehouse.repository;

import ca.chrisbritten.warehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
