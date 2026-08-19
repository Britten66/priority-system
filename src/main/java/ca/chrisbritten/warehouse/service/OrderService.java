package ca.chrisbritten.warehouse.service;

import ca.chrisbritten.warehouse.algorithm.OrderBST;
import ca.chrisbritten.warehouse.model.Order;
import ca.chrisbritten.warehouse.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderBST orderBST;

    public OrderService(OrderRepository orderRepository, OrderBST orderBST) {
        this.orderRepository = orderRepository;
        this.orderBST = orderBST;
    }

    @PostConstruct
    void rebuildTree() {
        orderBST.clear();
        orderRepository.findAll().forEach(orderBST::insert);
    }

    public Order createOrder(Order order) {
        Order saved = orderRepository.save(order);
        orderBST.insert(saved);
        return saved;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByPriority() {
        return orderBST.inorder();
    }

    public Order getHighestPriorityOrder() {
        Order highest = orderBST.findHighest();
        if (highest == null) {
            throw new NoSuchElementException("No orders exist");
        }
        return highest;
    }

    public Order getLowestPriorityOrder() {
        Order lowest = orderBST.findLowest();
        if (lowest == null) {
            throw new NoSuchElementException("No orders exist");
        }
        return lowest;
    }
}
