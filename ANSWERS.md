# Theory Questions

## Binary Search Tree

**Why does an inorder traversal of a BST return sorted results?**
Left-root-right visting hits everything in ascending order, since anything smaller than a node is always in its left subtree and anything bigger is in its right.

**What happens if you insert 1,2,3,4,5 in order? How does this affect performance?**
It degenerates into basically a linked list (everything just chains to the right). Lookups go from O(log n) down to O(n).

**Difference between average and worst-case time complexity for a BST?**
Average is O(log n) when the tree stays balanced. Worst case is O(n) when it's skewed, like the 1-2-3-4-5 example above.

**Where would you place duplicate priority values? Explain your choice.**
Right subtree. That way no order ever gets dropped, and ties keep their insertion order.

## Sorting Algorithm

**Explain how your sorting algorithm works, step-by-step, with a small example.**
Insertion sort. Example on `[5, 2, 4]`:
- Start with `[5]` sorted.
- Insert 2, shift 5 over → `[2, 5]`.
- Insert 4, shift 5 over → `[2, 4, 5]`.

**Time complexity of your algorithm?**
O(n²) average and worst case, O(n) if the list's already sorted.

**When would your sorting algorithm perform well?**
Small lists, or ones that are already close to sorted.

**Ideal or not ideal for very large datasets? Why?**
Not ideal, it gets slow fast once n gets big since it's quadratic.

## System Design

**Why sort data in your application instead of the database?**
Because the assignment wants a hand-written algorithm, not `ORDER BY`.

**One advantage of using a BST in this system?**
Priority order is always ready without re-sorting on every read.

**One limitation of your current design?**
It's not self-balancing, so a bad insert order (like sequential priorities) can degrade it to O(n).

# AI Usage Disclosure

I used Claude (Claude Code) mainly for debugging and checking my work against the assignment doc, not for writing things from scratch.

- Had it compare my endpoint list against the spec and point out where I didn't match — sorting was on a `?sort=` query param instead of `/products/sorted?by=`, and priority lookups were sitting at `/orders/highest`/`/orders/lowest` instead of `/orders/priority/...`.
- When I got a 500 error posting a customer through Postman, I gave it the error and had it check the server logs to find the actual cause — turned out I had an `id` field left in my request body, which made Hibernate treat it as an update instead of an insert.
- General workflow was: run something, and if it broke or didn't match spec, hand back the error/response and ask what was wrong, then fix it myself or have it make the change.
- BST, sorting algorithm, entities, and tests were already written before this pass.
