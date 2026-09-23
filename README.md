# Restaurant Auto Service

A Spring Boot REST API for automating restaurant back-office operations: inventory management, menu and dish composition, sales (purchases), and inventory analytics. The service is written in a mix of **Java** and **Kotlin** and stores its data in **MongoDB**.

## Features

- **Product & inventory management** — CRUD for warehouse products, with stock levels (`availableQuantity`, `maxQuantity`), pricing (`orderPrice`, `actualPrice`), supplier data, and ABC categories.
- **Dishes** — build dishes out of products (each dish references a list of product pairs with quantities), with automatic price/weight handling.
- **Menu** — group dishes into menus by type (breakfast, lunch, dinner, salads, grill, drinks, desserts, etc.).
- **Restaurants** — manage restaurant entities and their menus.
- **Purchases** — register waitress purchases (a set of dishes), which decrement product stock accordingly.
- **Orders** — create replenishment orders.
- **Users** — manage staff accounts with roles (`ADMIN`, `WAITREES`).
- **Analytics algorithms**:
  - **ABC analysis** — classifies products into A/B/C categories by cumulative value share (Pareto principle), using configurable A/B thresholds.
  - **Stock replenishment forecast** — calculates which products need to be reordered for the next day and how much to buy, based on a per-weekday demand percentage (higher on weekends).

## Tech stack

- Java 17 & Kotlin 1.8
- Spring Boot 3.0.2 (Web, Data MongoDB, Security)
- MongoDB
- Gradle (Kotlin DSL)
- Lombok
- JSP / JSTL (for basic server-rendered views)
- JUnit 5

## Prerequisites

- JDK 17
- A running MongoDB instance on `localhost:27017`

## Configuration

Settings live in [`src/main/resources/application.properties`](src/main/resources/application.properties):

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=RestaurantAutoServiceBeta
server.port=8080
```

Adjust the host, port, and database name to match your environment.

## Running the application

Start MongoDB, then run:

```bash
./gradlew bootRun
```

Or build a runnable JAR:

```bash
./gradlew build
java -jar build/libs/RestorauntAutoService-0.0.1-SNAPSHOT.jar
```

The API will be available at `http://localhost:8080`.

## Running tests

```bash
./gradlew test
```

## API overview

All endpoints return/accept JSON unless noted otherwise.

### Products — `/product`
| Method | Path | Description |
| --- | --- | --- |
| GET | `/product/product-find/{id}` | Find a product by id |
| GET | `/product/product-findAll` | List all products |
| POST | `/product/product-create` | Create a product |
| PUT | `/product/product-edit/{id}` | Update a product |
| DELETE | `/product/product-delete/{id}` | Delete a product by id |
| DELETE | `/product/product-delete-byName/{name}` | Delete a product by name |
| GET | `/product/product-count-rest` | Products to replenish for tomorrow |
| PUT | `/product/product-after-puchase` | Update stock after a purchase |

### Dishes — `/dish`
| Method | Path | Description |
| --- | --- | --- |
| GET | `/dish/dish-find/{id}` | Find a dish by id |
| GET | `/dish/dish-findAll` | List all dishes |
| POST | `/dish/dish-create` | Create a dish (from a dish request) |
| POST | `/dish/dish-create-postman` | Create a dish (raw model, for Postman) |
| PUT | `/dish/dish-edit/{name}` | Update a dish by name |
| DELETE | `/dish/dish-delete/{name}` | Delete a dish by name |

### Menus — `/menu`
| Method | Path | Description |
| --- | --- | --- |
| GET | `/menu/menu-find/{menuType}` | Find a menu by type |
| GET | `/menu/menu-findAll` | List all menus |
| POST | `/menu/menu-create` | Create a menu |
| PUT | `/menu/menu-edit/{menuType}` | Replace the dishes of a menu |
| DELETE | `/menu/menu-delete/{menuType}` | Delete a menu |

### Restaurants — `/restaurant`
| Method | Path | Description |
| --- | --- | --- |
| POST | `/restaurant/restaurant-create` | Create a restaurant |
| PUT | `/restaurant/restaurant-edit/{id}` | Update a restaurant |
| DELETE | `/restaurant/restaurant-delete/{id}` | Delete a restaurant |

### Purchases — `/purchase`
| Method | Path | Description |
| --- | --- | --- |
| POST | `/purchase/purchase-create` | Register a purchase |
| GET | `/purchase/purchase-find/{id}` | Find a purchase by id |
| DELETE | `/purchase/purchase-delete/{id}` | Delete a purchase |

### Orders — `/order`
| Method | Path | Description |
| --- | --- | --- |
| POST | `/order/order-create` | Create a replenishment order |
| PUT | `/order/order-edit/{id}` | Update an order |
| DELETE | `/order/order-delete/{id}` | Delete an order |
| GET | `/order/order-findAll` | List all orders |

### Users — `/user`
| Method | Path | Description |
| --- | --- | --- |
| POST | `/user/user-create` | Create a user |
| PUT | `/user/user-edit/{userName}` | Update a user |
| DELETE | `/user/user-delete/{userName}` | Delete a user |
| GET | `/user/user-findAll` | List all users |
| GET | `/user/user-find/{userName}` | Find a user by username |

### ABC analysis
| Method | Path | Description |
| --- | --- | --- |
| GET | `/abc-analysis/{percentA}/{percentB}` | Run ABC analysis with the given cumulative-value thresholds (e.g. `0.8` and `0.95`). Returns three product lists (A, B, C) and persists each product's category. |

## Project structure

```
src/main/java/com/work/restorauntautoservice/
├── controller/     REST controllers
├── service/        Service interfaces + implementation/
├── repository/     Spring Data MongoDB repositories
├── model/          Domain models & request DTOs
├── enums/          ProductCategory, MenuType, DayOfWeek, UserRole, Percentage
├── analysisAl/     ABC analysis & stock-replenishment algorithms (Kotlin)
├── config/         Web configuration
└── exception/      Custom exceptions
```

## License

No license file is provided. Contact the repository owner regarding usage.
