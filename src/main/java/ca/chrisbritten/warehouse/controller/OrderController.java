package ca.chrisbritten.warehouse.controller;

import ca.chrisbritten.warehouse.model.Order;
import ca.chrisbritten.warehouse.service.OrderService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getOrdersByPriority() {
        return orderService.getOrdersByPriority();
    }

    @GetMapping("/highest")
    public Order getHighestPriorityOrder() {
        return orderService.getHighestPriorityOrder();
    }

    @GetMapping("/lowest")
    public Order getLowestPriorityOrder() {
        return orderService.getLowestPriorityOrder();
    }
}
