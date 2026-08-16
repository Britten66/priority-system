package ca.chrisbritten.warehouse.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.chrisbritten.warehouse.model.Product;
import java.util.List;
import org.junit.jupiter.api.Test;

class ProductSorterTest {

    @Test
    void sortByPriceOrdersAscendingWithoutMutatingInput() {
        List<Product> products = List.of(
                new Product("A", 30, 10),
                new Product("B", 10, 10),
                new Product("C", 20, 10),
                new Product("D", 5, 10)
        );

        List<Product> sorted = ProductSorter.sortByPrice(products);

        assertEquals(5, sorted.get(0).getPrice());
        assertEquals(10, sorted.get(1).getPrice());
        assertEquals(20, sorted.get(2).getPrice());
        assertEquals(30, sorted.get(3).getPrice());

        assertEquals(30, products.get(0).getPrice());
        assertEquals(10, products.get(1).getPrice());
        assertEquals(20, products.get(2).getPrice());
        assertEquals(5, products.get(3).getPrice());
    }

    @Test
    void sortByStockOrdersAscending() {
        List<Product> products = List.of(
                new Product("A", 1.0, 5),
                new Product("B", 1.0, 50),
                new Product("C", 1.0, 1)
        );

        List<Product> sorted = ProductSorter.sortByStock(products);

        assertEquals(1, sorted.get(0).getStock());
        assertEquals(5, sorted.get(1).getStock());
        assertEquals(50, sorted.get(2).getStock());
    }
}
