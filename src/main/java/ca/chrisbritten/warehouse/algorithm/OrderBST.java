package ca.chrisbritten.warehouse.algorithm;

import ca.chrisbritten.warehouse.model.Order;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderBST {

    private OrderNode root;

    public void insert(Order order) {
        root = insertRecursive(root, order);
    }

    private OrderNode insertRecursive(OrderNode current, Order order) {
        if (current == null) {
            return new OrderNode(order);
        }

        if (order.getPriorityLevel() < current.getData().getPriorityLevel()) {
            current.setLeft(insertRecursive(current.getLeft(), order));
        } else {
            // Duplicates go right so no order is discarded and ties keep insertion order.
            current.setRight(insertRecursive(current.getRight(), order));
        }

        return current;
    }

    public List<Order> inorder() {
        List<Order> orders = new ArrayList<>();
        inorderRecursive(root, orders);
        return orders;
    }

    private void inorderRecursive(OrderNode current, List<Order> orders) {
        if (current == null) {
            return;
        }
        inorderRecursive(current.getLeft(), orders);
        orders.add(current.getData());
        inorderRecursive(current.getRight(), orders);
    }

    public Order findHighest() {
        if (root == null) {
            return null;
        }
        OrderNode current = root;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current.getData();
    }

    public Order findLowest() {
        if (root == null) {
            return null;
        }
        OrderNode current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getData();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }
}
