package ca.chrisbritten.warehouse.service;

import ca.chrisbritten.warehouse.algorithm.ProductSorter;
import ca.chrisbritten.warehouse.model.Product;
import ca.chrisbritten.warehouse.repository.ProductRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found: " + id));
    }

    public List<Product> getProductsSortedByPrice() {
        return ProductSorter.sortByPrice(productRepository.findAll());
    }

    public List<Product> getProductsSortedByStock() {
        return ProductSorter.sortByStock(productRepository.findAll());
    }
}
