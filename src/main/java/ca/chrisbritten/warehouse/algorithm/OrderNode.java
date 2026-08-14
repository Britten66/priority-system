package ca.chrisbritten.warehouse.algorithm;

import ca.chrisbritten.warehouse.model.Order;

public class OrderNode {

    private Order data;
    private OrderNode left;
    private OrderNode right;

    public OrderNode(Order data) {
        this.data = data;
    }

    public Order getData() {
        return data;
    }

    public void setData(Order data) {
        this.data = data;
    }

    public OrderNode getLeft() {
        return left;
    }

    public void setLeft(OrderNode left) {
        this.left = left;
    }

    public OrderNode getRight() {
        return right;
    }

    public void setRight(OrderNode right) {
        this.right = right;
    }
}
