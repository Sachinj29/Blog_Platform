# Blog Platform REST API

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.5-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-336791?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4.0.0-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

A robust, secure, and feature-rich RESTful API for a blog platform. This project serves as the complete backend for a content management system, handling users, roles, posts, comments, categories, and tags. It is built with a modern Java stack and follows best practices for API development, including JWT-based security and a clean, layered architecture.

## ✨ Key Features

*   **User Authentication & Authorization:** Secure registration and login endpoints using Spring Security and JSON Web Tokens (JWT).
*   **Role-Based Access Control:** Differentiated permissions for `USER` and `ADMIN` roles.
*   **Full CRUD for Posts:** Create, Read, Update, and Delete blog posts.
*   **Dynamic Content Management:** Full CRUD operations for Categories, Tags, and Comments.
*   **Secure Endpoints:** Protected routes that ensure only authenticated and authorized users can perform sensitive actions (e.g., only an author or admin can delete a post).
*   **Relational Data Model:** Well-structured PostgreSQL database schema to manage relationships between users, posts, comments, etc.
*   **DTO Pattern:** Utilizes Data Transfer Objects (DTOs) to expose a clean and secure API, separating it from the internal database structure.
*   **Centralized Exception Handling:** Graceful error handling for a consistent API response experience.

## 🚀 Technology Stack

| Technology | Description |
| :--- | :--- |
| **Java 17+** | The core programming language. |
| **Spring Boot** | Framework for building stand-alone, production-grade Spring applications. |
| **Spring MVC** | Used for building the RESTful API endpoints. |
| **Spring Data JPA** | Simplifies data access layer with repository pattern and Hibernate. |
| **Spring Security** | Handles all authentication and authorization logic. |
| **PostgreSQL** | Powerful, open-source relational database. |
| **JWT (Java Web Token)** | Used for securing API endpoints via tokens. |
| **Lombok** | Reduces boilerplate code (getters, setters, constructors). |
| **Maven** | Dependency management and project build tool. |

## 📁 Project Structure

The project follows a standard layered architecture to ensure separation of concerns and maintainability.

src/main/java/com/sachin/blog/
├── config/ # Spring Security and Data Initialization configuration
├── controller/ # REST API controllers (handling HTTP requests)
├── dto/ # Data Transfer Objects (for API requests and responses)
├── exception/ # Custom exception classes and global handler
├── model/ # JPA entity classes (database table mappings)
├── repository/ # Spring Data JPA repositories (for database operations)
├── security/ # JWT and UserDetailsService implementation
└── service/ # Business logic layer




