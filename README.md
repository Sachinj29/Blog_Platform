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




## ⚙️ Getting Started

Follow these instructions to get the project up and running on your local machine.

### Prerequisites

*   Java (JDK) 17 or later
*   Apache Maven
*   PostgreSQL Server

### Installation & Configuration

1.  **Clone the repository:**
    ```
    git clone https://github.com/your-username/your-repo-name.git
    cd your-repo-name
    ```

2.  **Create the PostgreSQL Database:**
    *   Open your PostgreSQL client (e.g., pgAdmin, DBeaver).
    *   Create a new database named `blog_platform`.

3.  **Configure the Application:**
    *   Navigate to `src/main/resources/`.
    *   Open the `application.properties` file.
    *   Update the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties to match your PostgreSQL setup.
    *   Replace the `app.jwt.secret` with your own unique, Base64-encoded secret key of at least 512 bits.

    ```
    # PostgreSQL Database Configuration
    spring.datasource.url=jdbc:postgresql://localhost:5432/blog_platform
    spring.datasource.username=your_postgres_username
    spring.datasource.password=your_postgres_password

    # JWT Secret Configuration
    app.jwt.secret=c29tZXRoaW5nLXJlYWxseS1zZWNyZXQtYW5kLWxvbmctZW5vdWdoLWZvci10aGUtSFM1MTItYWxnb3JpdGhtLW11c3QtYmUtbW9yZS10aGFuLTUxMi1iaXRz
    ```

4.  **Run the application:**
    *   You can run the application using your IDE (like IntelliJ IDEA) by running the `BlogApplication.java` file.
    *   Or, you can run it from the command line using Maven:
        ```
        mvn spring-boot:run
        ```
    The application will start on `http://localhost:8081`.

## 📖 API Documentation

The API is designed to be tested with tools like Postman.

### Authentication

| Endpoint | Method | Description | Auth Required? | Request Body |
| :--- | :--- | :--- |:--- |:--- |
| `/api/auth/register` | `POST` | Registers a new user. | No | `{ "username": "...", "email": "...", "password": "..." }` |
| `/api/auth/login` | `POST` | Authenticates a user and returns a JWT. | No | `{ "username": "...", "password": "..." }` |

**Workflow:** Always `/register` first, then `/login` to get the Bearer Token for authenticated requests.

### Posts

| Endpoint | Method | Description | Auth Required? | Request Body |
| :--- | :--- | :--- |:--- |:--- |
| `/api/posts` | `GET` | Retrieves a list of all posts. | No | (None) |
| `/api/posts` | `POST` | Creates a new post. | Yes | `{ "title": "...", "content": "...", "categoryId": 1, "tags": ["tag1"] }` |
| `/api/posts/{id}` | `GET` | Gets a single post by its ID. | No | (None) |
| `/api/posts/{id}` | `PUT` | Updates an existing post. | Yes (Author/Admin) | `{ "title": "...", "content": "...", "categoryId": 1, "tags": [...] }` |
| `/api/posts/{id}` | `DELETE` | Deletes a post. | Yes (Author/Admin) | (None) |

### Comments

| Endpoint | Method | Description | Auth Required? | Request Body |
| :--- | :--- | :--- |:--- |:--- |
| `/api/posts/{postId}/comments` | `GET` | Retrieves all comments for a post. | No | (None) |
| `/api/posts/{postId}/comments` | `POST` | Adds a new comment to a post. | Yes | `{ "content": "..." }` |

### Categories

| Endpoint | Method | Description | Auth Required? | Request Body |
| :--- | :--- | :--- |:--- |:--- |
| `/api/categories` | `GET` | Retrieves all categories. | No | (None) |
| `/api/categories` | `POST` | Creates a new category. | Yes (Admin only) | `{ "name": "..." }` |

---
