package ca.chrisbritten.warehouse.config;

import ca.chrisbritten.warehouse.model.Customer;
import ca.chrisbritten.warehouse.model.Order;
import ca.chrisbritten.warehouse.model.OrderItem;
import ca.chrisbritten.warehouse.model.Product;
import ca.chrisbritten.warehouse.repository.CustomerRepository;
import ca.chrisbritten.warehouse.repository.ProductRepository;
import ca.chrisbritten.warehouse.service.OrderService;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderService orderService;

    public DataSeeder(CustomerRepository customerRepository, ProductRepository productRepository,
            OrderService orderService) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) {
        if (customerRepository.count() > 0) {
            return;
        }

        Customer alice = customerRepository.save(new Customer("Alice Nguyen", "alice@example.com"));
        Customer bilal = customerRepository.save(new Customer("Bilal Khan", "bilal@example.com"));
        Customer chen = customerRepository.save(new Customer("Chen Li", "chen@example.com"));

        Product widget = productRepository.save(new Product("Widget", 9.99, 200));
        Product gadget = productRepository.save(new Product("Gadget", 24.50, 75));
        Product gizmo = productRepository.save(new Product("Gizmo", 4.25, 500));

        Order order1 = new Order(LocalDate.now().minusDays(2), 3, alice);
        order1.addItem(new OrderItem(5, widget, order1));
        order1.addItem(new OrderItem(1, gadget, order1));
        orderService.createOrder(order1);

        Order order2 = new Order(LocalDate.now().minusDays(1), 8, bilal);
        order2.addItem(new OrderItem(2, gizmo, order2));
        orderService.createOrder(order2);

        Order order3 = new Order(LocalDate.now(), 1, chen);
        order3.addItem(new OrderItem(10, widget, order3));
        orderService.createOrder(order3);
    }
}
