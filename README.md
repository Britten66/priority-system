# priority-system

Warehouse order-priority system built with Spring Boot, JPA, and H2 for my Data Structures and Algorithms final. Orders get ranked by priority using a binary search tree I wrote myself, and products can be sorted by price or stock using insertion sort I wrote myself (no built-in sort methods).

## Stack

Java 21, Spring Boot, Spring Web, Spring Data JPA, Bean Validation, H2 (file-based, saves to `./data/warehousedb`).

## Running it

```bash
./mvnw spring-boot:run
```

Starts on `http://localhost:8080`. On first run with an empty database, `DataSeeder` adds three customers, three products, and three orders with different priority levels so there's something to look at right away.

H2 console is at `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:file:./data/warehousedb`, user `SA`, no password).

## Running the tests

```bash
./mvnw test
```

## Domain model

A Customer can have many Orders. An Order belongs to one Customer and has many OrderItems. An OrderItem links one Order to one Product with a quantity. A Product can show up in many OrderItems.

## Endpoints

Customers
- POST /api/customers, create a customer
- GET /api/customers, list all customers
- GET /api/customers/{id}, get one customer

Products
- POST /api/products, create a product
- GET /api/products, list all products
- GET /api/products/sorted?by=price, products sorted by price
- GET /api/products/sorted?by=stock, products sorted by stock
- GET /api/products/{id}, get one product

Orders
- POST /api/orders, create an order (also inserts it into the priority tree)
- GET /api/orders, list all orders
- GET /api/orders/priority/inorder, orders sorted by priority (BST inorder traversal)
- GET /api/orders/priority/highest, highest priority order (rightmost node)
- GET /api/orders/priority/lowest, lowest priority order (leftmost node)

Invalid bodies on the POST endpoints return 400. Looking up something that doesn't exist returns 404.

## How the sorting and BST work

`ProductSorter` sorts with insertion sort, no `Collections.sort()` or streams.

`OrderBST` inserts orders by `priorityLevel`. Lower priority goes left, higher (or equal) goes right. Duplicate priorities go right too, so nothing gets dropped and ties keep the order they were inserted in. Inorder traversal (left, then node, then right) comes back sorted since that's how the tree was built. Highest priority is the rightmost node, lowest is the leftmost node.

The tree lives in memory. It gets rebuilt from the database on startup and updated live whenever a new order is created through the API.
