# Shop Backend API

[![Java Version](https://img.shields.io/badge/Java-25-orange.svg?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.16-brightgreen.svg?logo=springboot)](https://spring.io/projects/spring-boot)
[![Build Tool](https://img.shields.io/badge/Build-Maven%20%7C%20Gradle-blue.svg?logo=apachemaven)](https://maven.apache.org/)
[![Database](https://img.shields.io/badge/Database-MySQL%208.4-4479A1.svg?logo=mysql)](https://www.mysql.com/)
[![Database Migrations](https://img.shields.io/badge/Migrations-Liquibase-red.svg?logo=liquibase)](https://www.liquibase.org/)
[![Authentication](https://img.shields.io/badge/Auth-JWT-black.svg?logo=jsonwebtokens)](https://jwt.io/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A robust, headless RESTful e-commerce backend platform built with **Java 25** and **Spring Boot 3.5.16**. Designed as a modular API-first foundation, it allows developers to connect any custom frontend application (e.g. Angular, React, Vue, Next.js, or mobile clients) to handle end-to-end online store operations.

---

## Table of Contents

- [Project Name](#shop-backend-api)
- [Project Description](#project-description)
- [Tech Stack](#tech-stack)
  - [Core & Framework](#core--framework)
  - [Persistence & Database](#persistence--database)
  - [Security & Authentication](#security--authentication)
  - [Caching & Performance](#caching--performance)
  - [Integrations & Utilities](#integrations--utilities)
  - [Containerization & DevOps](#containerization--devops)
- [Getting Started Locally](#getting-started-locally)
  - [Prerequisites](#prerequisites)
  - [Configuration](#configuration)
  - [Option 1: Running with Docker Compose (Recommended)](#option-1-running-with-docker-compose-recommended)
  - [Option 2: Running Manually (Maven or Gradle)](#option-2-running-manually-maven-or-gradle)
- [Available Scripts](#available-scripts)
  - [Maven Commands](#maven-commands)
  - [Gradle Commands](#gradle-commands)
  - [Docker Commands](#docker-commands)
- [Project Scope](#project-scope)
  - [Customer-Facing Features](#customer-facing-features)
  - [Administrative Features (Backoffice)](#administrative-features-backoffice)
  - [Key API Endpoints](#key-api-endpoints)
- [Project Status](#project-status)
- [License](#license)

---

## Project Description

**Shop** is a scalable, headless e-commerce backend engineered to decouple core business logic and persistence from frontend presentation. It provides a complete API lifecycle for online merchandising, transactional processing, and administrative oversight:

- **Headless Architecture:** Standardized REST endpoints consumable by single-page web applications (SPA), server-side rendering frameworks (SSR), or native mobile apps.
- **Transactional Consistency:** Managed order workflows, cart retention, and automated reconciliation.
- **Secure by Design:** Stateless JSON Web Token (JWT) authorization, BCrypt credential hashing, role-based resource protection, and HTML sanitization for public submissions.
- **Automated Lifecycle Maintenance:** Liquibase-managed database versioning and scheduled background cleanup for abandoned carts.
- **Real-World Integrations:** Out-of-the-box integration with Przelewy24 (P24) payment gateway with webhook verification, SMTP notification support, and CSV data streaming for business reporting.

---

## Tech Stack

### Core & Framework
- **Language:** Java 25
- **Framework:** Spring Boot 3.5.16
- **Web Layer:** Spring Boot Starter Web (REST API), Spring WebFlux (WebClient for reactive external HTTP communication)
- **Validation:** Spring Boot Starter Validation (Hibernate Validator)
- **Boilerplate Reduction:** Project Lombok

### Persistence & Database
- **Relational Database:** MySQL 8.4
- **ORM / Data Access:** Spring Data JPA / Hibernate
- **Database Schema Migration:** Liquibase Core (27+ managed changelogs)

### Security & Authentication
- **Security Framework:** Spring Security
- **Token Mechanism:** Stateless JWT (`com.auth0:java-jwt 4.2.1`)
- **Password Encoding:** BCrypt (`{bcrypt}`)
- **HTML Sanitization:** Jsoup 1.18.1 (XSS prevention on reviews and inputs)

### Caching & Performance
- **Caching Framework:** Spring Cache Abstraction
- **Cache Provider:** Ehcache 3 (`org.ehcache:ehcache 3.10.8` with JCache JSR-107 API)
- **Cached Domains:** Product details by slug, category lists, product catalog by category, homepage promotions

### Integrations & Utilities
- **Payment Gateway:** Przelewy24 (P24) REST API v1 (register transaction, signature check, webhook IP verification)
- **Email Delivery:** Spring Boot Starter Mail (JavaMailSender / SMTP)
- **Slug Generation:** Slugify 2.4 (SEO-friendly product and category URLs)
- **Data Export:** Apache Commons CSV 1.9.0 & Commons IO 2.17.0
- **Documentation:** SpringDoc OpenAPI UI / Swagger 3 (`springdoc-openapi-ui 1.7.0`)

### Containerization & DevOps
- **Container Runtime:** Docker (multi-stage build with `eclipse-temurin:25-jdk-alpine` and `eclipse-temurin:25-jre-alpine`)
- **Orchestration:** Docker Compose (app, MySQL 8.4, Adminer, PhpMyAdmin)
- **Cloud Deployment:** Heroku-ready (`Procfile`, `system.properties`)

---

## Getting Started Locally

### Prerequisites
Before running the application, ensure the following are installed:
- **Java Development Kit (JDK):** Version 25 (or compatible JDK 21+ runtime)
- **Build Tool:** Either Apache Maven 3.9+ or Gradle 8+ (wrappers `./mvnw` and `./gradlew` are included in `backend/`)
- **Docker & Docker Compose:** Required for running the database stack or the entire containerized application

### Configuration
The application reads its configuration from [application.properties](file:///c:/Users/Dell/GIT/shop/backend/src/main/resources/application.properties). Default properties can be overridden using environment variables:

| Environment Variable | Description | Default Value |
|----------------------|-------------|---------------|
| `DATABASE_HOST` | Hostname of the MySQL database | `localhost` (or `mysql` in Docker) |
| `DATABASE_PORT` | Port of the MySQL database | `3306` |
| `DATABASE_NAME` | Database schema name | `shop` |
| `DATABASE_USERNAME` | Database user | `root` |
| `DATABASE_PASSWORD` | Database password | `toor` |
| `JWT_SECRET` | Secret key for JWT signature creation | *Preconfigured in properties* |
| `APP_UPLOAD_DIR` | Filesystem directory for product image uploads | `./data/productImages/` |

---

### Option 1: Running with Docker Compose (Recommended)

The repository includes a ready-to-use [docker-compose.yml](file:///c:/Users/Dell/GIT/shop/docker-compose.yml) file that provisions MySQL, database administration tools, and builds the backend service.

1. **Start all services:**
   ```bash
   docker-compose up --build
   ```
2. **Accessible Services:**
   - **Shop Backend API:** [http://localhost:8080](http://localhost:8080)
   - **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
   - **Adminer (DB UI):** [http://localhost:8082](http://localhost:8082)
   - **phpMyAdmin (DB UI):** [http://localhost:8081](http://localhost:8081)
   - **MySQL Database:** `localhost:3306` (`user: root`, `password: toor`, `database: shop`)

---

### Option 2: Running Manually (Maven or Gradle)

1. **Start only the MySQL database service:**
   ```bash
   docker-compose up -d mysql
   ```

2. **Navigate to the backend directory:**
   ```bash
   cd backend
   ```

3. **Run using Maven:**
   - On Linux/macOS:
     ```bash
     ./mvnw spring-boot:run
     ```
   - On Windows:
     ```powershell
     .\mvnw.cmd spring-boot:run
     ```

4. **Or run using Gradle:**
   - On Linux/macOS:
     ```bash
     ./gradlew bootRun
     ```
   - On Windows:
     ```powershell
     .\gradlew.bat bootRun
     ```

5. **Verify the application is up:**
   Visit [http://localhost:8080/products](http://localhost:8080/products) or [http://localhost:8080/categories](http://localhost:8080/categories).

---

## Available Scripts

The project includes Maven and Gradle wrappers, as well as Docker automation:

### Maven Commands (in `backend/`)
- **Compile and package application:**
  ```bash
  ./mvnw clean package
  ```
- **Package without executing tests:**
  ```bash
  ./mvnw clean package -DskipTests
  ```
- **Run the Spring Boot application:**
  ```bash
  ./mvnw spring-boot:run
  ```
- **Execute unit and integration tests:**
  ```bash
  ./mvnw test
  ```

### Gradle Commands (in `backend/`)
- **Build the project JAR:**
  ```bash
  ./gradlew build
  ```
- **Build without running tests:**
  ```bash
  ./gradlew build -x test
  ```
- **Run the Spring Boot application:**
  ```bash
  ./gradlew bootRun
  ```
- **Run test suite:**
  ```bash
  ./gradlew test
  ```

### Docker Commands (in project root)
- **Build and run all services in background:**
  ```bash
  docker-compose up -d --build
  ```
- **Stop all services:**
  ```bash
  docker-compose down
  ```
- **Stop services and remove database volumes:**
  ```bash
  docker-compose down -v
  ```
- **View backend container logs:**
  ```bash
  docker-compose logs -f shop
  ```

---

## Project Scope

### Customer-Facing Features
- **Product Catalog & Browsing:**
  - Paginated product listing (`/products`).
  - SEO-friendly URL lookup using unique product slugs (`/products/{slug}`).
  - Regular price vs. promotional discount price (`salePrice`) presentation.
  - Dedicated homepage showcase for featured and discounted items (`/homePage`).
- **Category Hierarchy:**
  - Category listings with SEO slugs (`/categories`).
  - Filtered category products view with pagination (`/categories/{slug}/products`).
- **Cart Management:**
  - Anonymous and authenticated shopping cart creation and retrieval (`/carts/{id}`).
  - Add items, bulk update quantities, remove items (`/cartItems/{id}`).
  - Live cart item counter (`/cartItems/count/{cartId}`).
  - Background automated cleanup for abandoned carts older than 3 days (`@Scheduled` cron job).
- **Checkout & Ordering:**
  - Dynamic checkout metadata initialization for available shipping methods and payment providers (`/orders/initData`).
  - Order submission with customer details, selected delivery method, and payment choice (`POST /orders`).
  - Hash-based order status verification (`/orders/notification/{orderHash}`).
  - Customer order history tracking for authenticated accounts (`GET /orders`).
- **Payment Processing:**
  - Integration with **Przelewy24 (P24)** payment service.
  - Asynchronous webhook notification receiver with IP whitelisting validation (`POST /orders/notification/{orderHash}`).
- **Reviews & Feedback:**
  - Submit product reviews with author name and text (`POST /reviews`).
  - Sanitization of user input via Jsoup Safelist to prevent Cross-Site Scripting (XSS).
- **Authentication & User Account:**
  - User registration (`POST /register`) with password matching validation and BCrypt hashing.
  - User login (`POST /login`) issuing JWT tokens.
  - Password recovery workflow via email tokens (`POST /lostPassword` and `POST /changePassword`).

---

### Administrative Features (Backoffice)
All admin endpoints reside under the `/admin/**` path and are strictly protected with `ROLE_ADMIN` authority:
- **Product Management:**
  - Full CRUD operations for products (`/admin/products`).
  - Multipart image upload (`/admin/products/upload-image`) and static image delivery (`/data/productImage/{filename}`).
  - Automatic URL slug generation via Slugify.
  - Targeted cache eviction for single products or complete product cache flushing (`/admin/products/clearCache`).
- **Category Management:**
  - Full CRUD operations for product categories (`/admin/categories`).
  - Automatic slugification of category names.
- **Order Administration:**
  - Paginated listing and inspection of all customer orders (`/admin/orders`).
  - Order status workflow transitions via PATCH (`NEW`, `PAID`, `PROCESSING`, `SHIPPED`, `COMPLETED`, `CANCELLED`).
- **Sales Analytics & Reporting:**
  - Real-time sales statistics and metrics (`/admin/orders/stats`).
  - Export filtered orders to CSV format based on date range (`from`, `to`) and status (`/admin/orders/export`).
- **Review Moderation:**
  - Review queue inspection (`/admin/reviews`).
  - Review approval / moderation toggling (`PUT /admin/reviews/{id}/moderate`).
  - Review deletion (`DELETE /admin/reviews/{id}`).

---

### Key API Endpoints

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| `POST` | `/login` | Public | Authenticate user and receive JWT token |
| `POST` | `/register` | Public | Register new customer account |
| `POST` | `/lostPassword` | Public | Request password reset email |
| `POST` | `/changePassword` | Public | Reset password using secret hash |
| `GET` | `/products` | Public | Retrieve paginated list of products |
| `GET` | `/products/{slug}` | Public (Cached) | Retrieve detailed product by slug |
| `GET` | `/categories` | Public (Cached) | Retrieve all categories |
| `GET` | `/categories/{slug}/products` | Public (Cached) | Retrieve category with products |
| `GET` | `/homePage` | Public (Cached) | Retrieve sale products for homepage |
| `GET` | `/carts/{id}` | Public | Retrieve shopping cart summary |
| `PUT` | `/carts/{id}` | Public | Add item to cart |
| `PUT` | `/carts/{id}/update` | Public | Update cart items list |
| `DELETE` | `/cartItems/{id}` | Public | Remove individual cart item |
| `GET` | `/cartItems/count/{cartId}` | Public | Count total items in cart |
| `POST` | `/orders` | Public / Customer | Place a new customer order |
| `GET` | `/orders` | Authenticated | List orders placed by current user |
| `GET` | `/orders/initData` | Public | Get available payment and shipment methods |
| `POST` | `/reviews` | Public | Add product review (sanitized via Jsoup) |
| `GET` | `/admin/products` | `ROLE_ADMIN` | List products with pagination |
| `POST` | `/admin/products` | `ROLE_ADMIN` | Create a new product |
| `POST` | `/admin/products/upload-image`| `ROLE_ADMIN` | Upload product image |
| `GET` | `/admin/orders` | `ROLE_ADMIN` | View customer orders |
| `PATCH` | `/admin/orders/{id}` | `ROLE_ADMIN` | Update order status |
| `GET` | `/admin/orders/stats` | `ROLE_ADMIN` | Retrieve order analytics |
| `GET` | `/admin/orders/export` | `ROLE_ADMIN` | Export orders to CSV file |
| `GET` | `/admin/reviews` | `ROLE_ADMIN` | List submitted customer reviews |
| `PUT` | `/admin/reviews/{id}/moderate`| `ROLE_ADMIN` | Approve / moderate customer review |

> **Default Administrator Credentials:**  
> The database migration initializes a default admin user:
> - **Username:** `admin`
> - **Password:** `admin` (or change via database / migration `20-add-admin-user.sql`)

---

## Project Status

- **Status:** **Active Development / Functional MVP**
- **Architecture Maturity:** The core headless REST API is complete, covering catalog management, basket retention, order lifecycle, payments via P24, review moderation, caching, and admin analytics.
- **Frontend Compatibility:** The API is structured to connect with any modern client. By default, CORS and return URLs anticipate local frontends running on `http://localhost:4200` (e.g. Angular) as well as production web hosts.
- **Planned Enhancements:**
  - Integration testing expansions for end-to-end payment callbacks.
  - Multi-currency / internationalized tax calculations.
  - Elasticsearch / Full-text search engine integration for high-volume catalogs.

---

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT). You are free to use, modify, distribute, and integrate this software in personal and commercial applications.
