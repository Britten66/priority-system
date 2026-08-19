# Theory Questions

## Binary Search Tree

**Why does an inorder traversal of a BST return sorted results?**
Left-root-right visits ascending order.

**What happens if you insert 1,2,3,4,5 in order? How does this affect performance?**
Degenerates into a linked list. Lookups drop from O(log n) to O(n).

**Difference between average and worst-case time complexity for a BST?**
Average: O(log n), balanced tree. Worst: O(n), skewed tree.

**Where would you place duplicate priority values? Explain your choice.**
Right subtree. Keeps every order, preserves insertion order among ties.

## Sorting Algorithm

**Explain how your sorting algorithm works, step-by-step, with a small example.**
Insertion sort. Example on `[5, 2, 4]`:
- Start with `[5]` sorted.
- Insert 2: shift 5 right → `[2, 5]`.
- Insert 4: shift 5 right → `[2, 4, 5]`.

**Time complexity of your algorithm?**
O(n²) average/worst case, O(n) best case (already sorted).

**When would your sorting algorithm perform well?**
Small lists, or lists that are already mostly sorted.

**Ideal or not ideal for very large datasets? Why?**
Not ideal — quadratic growth makes it slow at scale.

## System Design

**Why sort data in your application instead of the database?**
Assignment requires a hand-written algorithm, not `ORDER BY`.

**One advantage of using a BST in this system?**
Priority order is always available without re-sorting on every read.

**One limitation of your current design?**
Tree isn't self-balancing — worst-case insert order degrades to O(n).

# AI Usage Disclosure

- Used Claude Code to check my endpoint list against the assignment spec and flag two mismatches: sorting was on a `?sort=` query param instead of `/products/sorted?by=`, and priority lookups were at `/orders/highest` and `/orders/lowest` instead of under `/orders/priority/...`.
- Had it make those two routing changes (`ProductController`, `OrderController`, `OrderService`, plus a 400 handler for a bad `by` value) and confirmed the build and existing tests still pass.
- BST, sorting algorithm, entities, and tests were already in place before this pass.
