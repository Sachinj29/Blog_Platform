# Blog Platform REST API

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.5-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-336791?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4.0.0-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![React](https://img.shields.io/badge/React-18-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![JavaScript](https://img.shields.io/badge/JavaScript-ES6+-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)


The core of this project is a robust, secure, and feature-rich RESTful API for a blog platform. Built with a modern Java stack (Spring Boot, Spring Security), the backend provides a comprehensive content management system, handling users, roles, posts, comments, categories, and tags. It emphasizes API development best practices through JWT-based security and a clean, layered architecture.

To showcase its capabilities, the project also includes a functional React frontend. This optional UI client demonstrates how to consume the API for user registration, login, and post management, serving as a practical example of a client application.


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


## 🛡️ Security Architecture

The project employs a robust, two-layered security approach to protect user data and secure API endpoints.

*   **Password Hashing (bcrypt):** All user passwords are **never stored in plain text**. They are securely hashed using the industry-standard **bcrypt** algorithm (`BCryptPasswordEncoder`). Bcrypt is an adaptive hashing function that is intentionally slow and includes a random salt, making it highly resistant to brute-force and rainbow table attacks.

*   **API Security (HMAC-SHA512):** API endpoints are secured using JSON Web Tokens (JWTs). Each token is digitally signed using the strong **HMAC-SHA512 (HS512)** algorithm and a confidential secret key. This signature guarantees two things: **Authentication** (the token was issued by our server) and **Integrity** (the token's data has not been tampered with since it was issued).


## ⚙️ Getting Started

Follow these instructions to get the project up and running on your local machine.

### Prerequisites

*   Java (JDK) 17 or later
*   Apache Maven
*   PostgreSQL Server

### Installation & Configuration

1.  **Clone the repository:**
    ```
    git clone https://github.com/Sachinj29/Blog_Platform.git
    cd Blog_Platform
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



### 🎨 Frontend (React UI)

The frontend is a dynamic and responsive Single-Page Application (SPA) built with React. It provides a clean, modern user interface for interacting with the backend API, allowing users to register, log in, view posts, create new content, and manage their own posts in a seamless experience.

#### Frontend Technology Stack

| Technology | Description |
| :--- | :--- |
| **React** | The core library for building the user interface. |
| **React Router** | For client-side routing and navigation between pages. |
| **Axios** | A promise-based HTTP client for making API calls to the backend. |
| **React Context API** | Used for global state management, specifically for user authentication. |
| **jwt-decode** | A small library to decode JWTs on the client-side to access user information. |
| **CSS** | Custom CSS for a modern, animated, and responsive design. |

#### UI Features Implemented

*   **Responsive Navbar:** A sticky navigation bar that changes dynamically based on the user's authentication status.
*   **User Authentication:** Clean and user-friendly forms for user registration and login.
*   **Homepage Feed:** Displays all blog posts in a clean, card-based layout with hover animations.
*   **Full Post Management (CRUD):**
    *   **Create:** Logged-in users can access a dedicated page with a form to create new posts.
    *   **Read:** Anyone can view a list of all posts or click on a post to see its full details and comments.
    *   **Update:** A dedicated "Edit Post" page pre-populates a form with the post's existing content for easy editing.
    *   **Delete:** Users can delete their posts with a confirmation step to prevent accidental deletions.
*   **Conditional UI and Authorization:** The "Edit" and "Delete" buttons are dynamically rendered on the Post Details page, visible **only** to the original author of the post, ensuring users can only manage their own content.

#### Running the Frontend

To run the frontend application locally, follow these steps.

**Prerequisites:**
*   Node.js and npm
*   **Important:** The backend Spring Boot server must be running first.

1.  **Navigate to the frontend directory:**
    ```
    cd blog-frontend
    ```

2.  **Install dependencies:**
    This will install React and all other necessary libraries.
    ```
    npm install
    ```

3.  **Start the development server:**
    ```
    npm start
    ```
    The application will automatically open in your browser at `http://localhost:3000`.


---
