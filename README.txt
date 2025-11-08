Customer Order Tracker (E-Commerce) — Microservices
===================================================

Modules
-------
- common-library: Shared DTOs and utilities
- customer-service: Customer profile CRUD
- product-service: Product catalog CRUD
- inventory-service: Stock management
- order-service: Place/update/cancel orders, integrates with inventory and customers
- api-gateway: Spring Cloud Gateway for routing

Build & Run (Dev)
-----------------
1) Java 11+, Maven 3.6+
2) From project root:
   mvn clean package
3) Start each service (example):
   cd customer-service && mvn spring-boot:run
   cd product-service && mvn spring-boot:run
   cd inventory-service && mvn spring-boot:run
   cd order-service && mvn spring-boot:run
   cd api-gateway && mvn spring-boot:run

Ports
-----
- api-gateway: 8080
- customer-service: 8081
- product-service: 8082
- inventory-service: 8083
- order-service: 8084

Sample Endpoints
----------------
Customer:
  POST   /customers
  GET    /customers/{id}
  PUT    /customers/{id}
  DELETE /customers/{id}
  GET    /customers (list)

Product:
  POST   /products
  GET    /products/{id}
  PUT    /products/{id}
  DELETE /products/{id}
  GET    /products (list)

Inventory:
  GET    /inventory/{productId}/stock
  PUT    /inventory/{productId}/stock?quantity=10

Order:
  POST   /orders (place order)
  GET    /orders
  PUT    /orders/{id}/status?value=SHIPPED|CANCELLED
  GET    /orders/customers (customers with order history - demo aggregation)

Gateway routes map /api/* to services.

Testing
-------
mvn test  (JUnit 5)
Generates surefire reports under each module: target/surefire-reports

Notes
-----
- H2 in-memory DB by default for quick start.
- Replace with MySQL by updating application.properties in each service.
- Inter-service calls are shown using RestTemplate (can swap for OpenFeign).
