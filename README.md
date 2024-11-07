
# ShopSmart API

The ShopSmart API is a backend RESTful API built with Spring Boot, designed to power e-commerce platforms with seamless functionality.

This API enables essential e-commerce operations such as user management, product catalog browsing, order processing, and cart management. 

With built-in security, session management, and admin controls, ShopSmart API provides a robust foundation for scaling and customizing e-commerce solutions.




## Tech Stack

**Client:** React, Redux, TailwindCSS

**Server:** Node, Express
- Java 17
- Spring Boot
  - Spring Data JPA
  - Spring Web
  - Spring Security
  - Spring Data Redis
- MySQL
- Redis
- Hibernate

## Features

- User Management: Registration, login, logout, and profile management.
- Product Catalog: Browse products with pagination.
- Order Management: Create, view, and delete orders.
- Cart Management: Add, view, and modify items in the shopping cart.
- Admin Functionalities: Admin-only access to manage users, products, and orders.
- Session Management: Redis-based session management for scalable session handling


## Installation

### Prerequisites

- Java 17
- Maven
- MySQL Database with a schema named ecommerce_db
- Redis server running on localhost at port 6379

### Setup

1. Clone the repository:

```bash
   git clone https://github.com/jenojiji/ShopSmart-API.git
   cd ShopSmart-API
```

2. Configure the Database:
    - Ensure MySQL is running on localhost with database name ecommerce_db, and set the username and password in application.properties:

```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
   spring.datasource.username=root
   spring.datasource.password=root

```    
3. Install Dependencies:
```bash
mvn install

```

4. Run the Application:
    - Start the application:

```bash
mvn spring-boot:run

```
## API Reference

#### API Endpoints : USER

```https
  GET /api/users/5
```
```https
  PUT /api/users/5
```
``` https
   DELETE /api/users/5
```

#### API Endpoints : ORDER

``` https
  POST /api/orders
```
``` https
  GET /api/orders/user/8
```
``` https
  DELETE /api/orders/5
```

#### API Endpoints : PRODUCT

```https
  GET /api/products?pageNo=1&pageSize=5
```
```https
  GET /api/products/7
```

#### API Endpoints : AUTH

```https
  POST /api/auth/login
```
```https
  POST /api/auth/register
```
```https
  POST /api/auth/logout
```

#### API Endpoints : ADMIN

```https
  GET /api/admin/users
```
```https
  GET /api/admin/orders
```
```https
  POST /api/admin/products
```
```https
  PUT /api/admin/products/6
```
```https
  DELETE /api/admin/products/1
```
```https
  GET /api/admin/products?pageNo=2&pageSize=12
```
```https
  GET /api/admin/products/4
```

#### API Endpoints : CART

```https
  POST /api/cart/add?userId=1&productId=1&quantity=2
```
```https
  GET /api/cart?userId=1
```
