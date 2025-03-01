# Neo Shop - Spring Boot E-Commerce Application

Welcome to **Neo Shop**, a Spring Boot-powered eCommerce application. This project serves as a backend solution for an
online shopping platform, featuring product management, user authentication, and order processing.

## 📌 Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)

---

# Features

- User authentication (JWT-based login & registration)
- Product management (CRUD operations)
- Shopping cart and order management
- Role-based access control (Admin & User)
- Secure payment gateway integration (Placeholder)
- REST-ful API endpoints
- H2 database for development, MySQL for production

---

# Technologies Used

- **Backend**: Spring Boot, Spring Security, Spring Data JPA
- **Database**: MySQL
- **Authentication**: JWT (JSON Web Tokens)
- **Build Tool**: Maven
- **Testing Tool**: Postman

---

# Installation

1. **Clone the repository**
   ```sh
   git clone https://github.com/your-username/neo-shop.git
   cd neo-shop
2. **Configure Database**
   Update application.properties for MySQL.
    ```properties
    #server
    server.port=${SPRING_NEO_SHOP_SERVER_PORT}
    
    #mysql
    spring.datasource.url=${SPRING_NEO_SHOP_MYSQL_URL}
    spring.datasource.username=${SPRING_NEO_SHOP_MYSQL_USER}
    spring.datasource.password=${SPRING_NEO_SHOP_MYSQL_PASS}
    
    #hibernate
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.hibernate.ddl-auto=update
    
    #image size
    spring.servlet.multipart.max-file-size=5MB
    spring.servlet.multipart.max-request-size=5MB
    ```
3. **.env config**
   ```properties
    SPRING_NEO_SHOP_MYSQL_PASS=PASSWORD
    SPRING_NEO_SHOP_MYSQL_URL=jdbc:mysql://localhost:3306/DB_NAME
    SPRING_NEO_SHOP_MYSQL_USER=USERNAME
    SPRING_NEO_SHOP_SERVER_PORT=SERVER_PORT
   ```
4. **Build & Run the Application**

---

# Database Schema
<details>
  <summary><b>Product</b></summary>

| Field       | Type          | Null | Key | Default | Extra          |
  |-------------|---------------|------|-----|---------|----------------|
| id          | bigint        | NO   | PRI | NULL    | auto_increment |
| brand       | varchar(255)  | YES  |     | NULL    |                |
| description | varchar(255)  | YES  |     | NULL    |                |
| name        | varchar(255)  | YES  |     | NULL    |                |
| price       | decimal(38,2) | YES  |     | NULL    |                |
| quantity    | int           | NO   |     | NULL    |                |
| category_id | bigint        | YES  | MUL | NULL    |                |

</details>
<details>
  <summary><b>Category</b></summary>

| Field | Type         | Null | Key | Default | Extra          |
|-------|--------------|------|-----|---------|----------------|
| id    | bigint       | NO   | PRI | NULL    | auto_increment |
| name  | varchar(255) | YES  |     | NULL    |                |

</details>

<details>
  <summary><b>Image</b></summary>

| Field      | Type         | Null | Key | Default | Extra          |
|------------|--------------|------|-----|---------|----------------|
| id         | bigint       | NO   | PRI | NULL    | auto_increment |
| file_name  | varchar(255) | YES  |     | NULL    |                |
| file_type  | varchar(255) | YES  |     | NULL    |                |
| image      | longblob     | YES  |     | NULL    |                |
| url        | varchar(255) | YES  |     | NULL    |                |
| product_id | bigint       | YES  | MUL | NULL    |                |

</details>


---

# API Endpoints

---

# Project Structure
```bash
NeoShop/
│── src/main/java/com/suraj/NeoShop/
│   ├── controller/     # Handles API requests
│   ├── dto/            # Data Transfer Objects
│   ├── exception/      # Custom exception handling
│   ├── mapper/         # Mapping DTOs to entities and vice versa
│   ├── model/          # Entity classes
│   ├── repository/     # Data access layer (Spring Data JPA repositories)
│   ├── request/        # Request payload models
│   ├── response/       # Response payload models
│   ├── service/        # Service interfaces
│   ├── serviceImpl/    # Service implementation classes
│── NeoShopApplication  # Main Spring Boot application entry point
```

---
