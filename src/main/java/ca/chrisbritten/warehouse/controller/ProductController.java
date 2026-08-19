
package ca.chrisbritten.warehouse.controller;

import ca.chrisbritten.warehouse.model.Product;
import ca.chrisbritten.warehouse.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/sorted")
    public List<Product> getSortedProducts(@RequestParam String by) {
        if ("price".equalsIgnoreCase(by)) {
            return productService.getProductsSortedByPrice();
        }
        if ("stock".equalsIgnoreCase(by)) {
            return productService.getProductsSortedByStock();
        }
        throw new IllegalArgumentException("Unsupported sort field: " + by);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
