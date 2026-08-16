package ca.chrisbritten.warehouse.service;

import ca.chrisbritten.warehouse.algorithm.OrderBST;
import ca.chrisbritten.warehouse.model.Order;
import ca.chrisbritten.warehouse.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
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

    public List<Order> getOrdersByPriority() {
        return orderBST.inorder();
    }

    public Order getHighestPriorityOrder() {
        return orderBST.findHighest();
    }

    public Order getLowestPriorityOrder() {
        return orderBST.findLowest();
    }
}
