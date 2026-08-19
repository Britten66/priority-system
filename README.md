# priority-system

A small warehouse order-priority system built with Spring Boot, JPA, and H2. Orders are
ranked by priority using a hand-written binary search tree, and products can be sorted by
price or stock using a hand-written insertion sort.

## Stack

- Java 21, Spring Boot 4
- Spring Web, Spring Data JPA, Bean Validation
- H2 (file-based, persists to `./data/warehousedb`)

## Running it

```bash
./mvnw spring-boot:run
```

The app starts on `http://localhost:8080`. On first run (empty database), `DataSeeder`
inserts three customers, three products, and three orders with different priority levels
so the sorting/BST endpoints have something to show immediately.

The H2 console is available at `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:file:./data/warehousedb`, user: `SA`, no password).

## Running the tests

```bash
./mvnw test
```

## Domain model

- **Customer** — has many **Orders**
- **Order** — belongs to a Customer, has many **OrderItems**
- **Product** — has many OrderItems
- **OrderItem** — join entity linking an Order to a Product with a quantity

## Endpoints

| Method | Path                        | Description                                                   | Success | Failure |
|--------|-----------------------------|-----------------------------------------------------------------|---------|---------|
| POST   | `/api/customers`            | Create a customer                                               | 200     | 400 (invalid body) |
| GET    | `/api/customers`             | List all customers                                               | 200     | — |
| GET    | `/api/customers/{id}`        | Get one customer by id                                           | 200     | 404 (not found) |
| POST   | `/api/products`              | Create a product                                                 | 200     | 400 (invalid body) |
| GET    | `/api/products`              | List products; `?sort=price` or `?sort=stock` to sort            | 200     | — |
| GET    | `/api/products/{id}`         | Get one product by id                                            | 200     | 404 (not found) |
| POST   | `/api/orders`                | Create an order (inserts into the BST)                           | 200     | 400 (invalid body) |
| GET    | `/api/orders`                | List all orders, in ascending priority order (BST inorder)       | 200     | — |
| GET    | `/api/orders/highest`        | Highest-priority order (BST rightmost node)                      | 200     | 404 (no orders) |
| GET    | `/api/orders/lowest`         | Lowest-priority order (BST leftmost node)                        | 200     | 404 (no orders) |

Product sorting (`?sort=price` / `?sort=stock`) and order priority ranking are implemented
by hand — `ProductSorter` uses insertion sort, `OrderBST` is a hand-rolled binary search
tree keyed on `priorityLevel` (duplicates inserted to the right, so ties keep insertion
order and no order is discarded).

## Validation and error handling

Bean Validation annotations on the entities (`@NotBlank`, `@Email`, `@PositiveOrZero`,
`@Min`, `@NotNull`) are enforced via `@Valid` on the `POST` endpoints. A
`@RestControllerAdvice` (`ValidationExceptionHandler`) maps:

- `MethodArgumentNotValidException` → `400 Bad Request` with a field → message map
- `NoSuchElementException` (missing customer/product by id, or highest/lowest on an
  empty order tree) → `404 Not Found`
