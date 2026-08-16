package ca.chrisbritten.warehouse.controller;

import ca.chrisbritten.warehouse.model.Product;
import ca.chrisbritten.warehouse.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
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
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String sort) {
        if ("price".equalsIgnoreCase(sort)) {
            return productService.getProductsSortedByPrice();
        }
        if ("stock".equalsIgnoreCase(sort)) {
            return productService.getProductsSortedByStock();
        }
        return productService.getAllProducts();
    }
}
