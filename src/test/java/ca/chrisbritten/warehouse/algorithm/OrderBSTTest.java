package ca.chrisbritten.warehouse.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ca.chrisbritten.warehouse.model.Order;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderBSTTest {

    private Order buildOrder(int priority) {
        return new Order(LocalDate.now(), priority, null);
    }

    @Test
    void inorderReturnsOrdersInAscendingPriority() {
        OrderBST tree = new OrderBST();
        int[] priorities = {5, 3, 8, 1, 9, 4};
        for (int priority : priorities) {
            tree.insert(buildOrder(priority));
        }

        List<Order> result = tree.inorder();

        assertEquals(6, result.size());
        assertEquals(1, result.get(0).getPriorityLevel());
        assertEquals(9, result.get(result.size() - 1).getPriorityLevel());
    }

    @Test
    void findHighestAndFindLowestReturnExtremes() {
        OrderBST tree = new OrderBST();
        int[] priorities = {5, 2, 10, 7};
        for (int priority : priorities) {
            tree.insert(buildOrder(priority));
        }

        assertEquals(10, tree.findHighest().getPriorityLevel());
        assertEquals(2, tree.findLowest().getPriorityLevel());
    }

    @Test
    void emptyTreeAndDuplicatePrioritiesAreHandled() {
        OrderBST tree = new OrderBST();

        assertTrue(tree.isEmpty());
        assertNull(tree.findHighest());
        assertNull(tree.findLowest());

        tree.insert(buildOrder(4));
        tree.insert(buildOrder(4));
        tree.insert(buildOrder(4));

        assertEquals(3, tree.inorder().size());
        assertEquals(4, tree.findHighest().getPriorityLevel());
    }
}
