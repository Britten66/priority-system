package ca.chrisbritten.warehouse.algorithm;

import ca.chrisbritten.warehouse.model.Product;
import java.util.ArrayList;
import java.util.List;

public final class ProductSorter {

    private ProductSorter() {
    }

    public static List<Product> sortByPrice(List<Product> products) {
        List<Product> sorted = new ArrayList<>(products);

        for (int i = 1; i < sorted.size(); i++) {
            Product current = sorted.get(i);
            int j = i - 1;
            while (j >= 0 && sorted.get(j).getPrice() > current.getPrice()) {
                sorted.set(j + 1, sorted.get(j));
                j--;
            }
            sorted.set(j + 1, current);
        }

        return sorted;
    }

    public static List<Product> sortByStock(List<Product> products) {
        List<Product> sorted = new ArrayList<>(products);

        for (int i = 1; i < sorted.size(); i++) {
            Product current = sorted.get(i);
            int j = i - 1;
            while (j >= 0 && sorted.get(j).getStock() > current.getStock()) {
                sorted.set(j + 1, sorted.get(j));
                j--;
            }
            sorted.set(j + 1, current);
        }

        return sorted;
    }
}
