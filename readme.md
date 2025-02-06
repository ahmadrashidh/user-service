# User Service

## Overview

The **User Service** is a Spring Boot microservice responsible for user authentication, authorization, and profile management for Ecommerce application. It provides a secure and scalable solution for handling user accounts, leveraging JWT-based authentication and role-based access control (RBAC) using Spring Authorization.

## System Design

### **Architecture**

The service follows a **MVC architecture**:

- **Controller Layer (`com.ahmad.user_service.controllers`)**
    - Exposes RESTful endpoints for user-related operations.
    - Handles request validation and response formatting.

- **Service Layer (`com.ahmad.user_service.services`)**
    - Implements business logic for user registration, authentication, and profile management.
    - Encapsulates interactions with the repository layer.

- **Repository Layer (`com.ahmad.user_service.repositories`)**
    - Interfaces with the database using JPA and Hibernate.
    - Provides CRUD operations on `User` and `Role` entities.

- **Security Layer (`com.ahmad.user_service.securities`)**
    - Implements authentication and authorization using **Spring Security** and **JWT**.
    - Configures security filters and user access restrictions.

### **Database Schema**

The service uses **H2 (in-memory) database** for development and supports **MySQL/PostgreSQL** for production.

#### **Tables**
- `users`: Stores user credentials and profile information.
- `roles`: Defines user roles (`CUSTOMER`, `ADMIN`).
- `user_roles`: Establishes a many-to-many relationship between users and roles.

### **Authentication & Authorization**

- **JWT-based authentication**: Upon successful login, users receive a **JWT token**, which must be included in subsequent requests for authentication.
- **Role-based access control (RBAC)**: Certain endpoints are restricted based on user roles.

## Technologies Used

- **Java 11**
- **Maven**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **Hibernate & JPA**
- **MySQL**


## API Endpoints

| Endpoint         | Method | Description | Authentication |  
|------------------|--------|-------------|----------------|  
| `/api/user/signup` | `POST` | Register a new user | No             |  
| `/api/user/login` | `POST` | Authenticate and receive JWT token | No             |  
| `/api/user/{id}` | `GET` | Retrieve user profile | No             |  
| `/api/user/{id}` | `PUT` | Update user profile | No             |  
| `/api/user/{id}` | `DELETE` | Delete user account | No (Admin)     |  

**Authentication:** JWT tokens must be included in the `Authorization` header as `Bearer <token>`.

## Deployment & Configuration

### **Running Locally**

1. **Clone the repository:**

   ```bash
   git clone https://github.com/ahmadrashidh/user-service.git
   cd user-service
   ```

2. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

3. The service starts at `http://localhost:9000`.

### **Configuration Properties**

Modify `application.properties` to switch between different database configurations:

## Security Implementation

1. **Password Hashing**
    - User passwords are hashed using **BCrypt** before storage.

2. **JWT Token Authentication**
    - Upon successful login, the system generates a **JWT token**.
    - The token is validated on every request to ensure security.

3. **Role-Based Access Control**
    - Only authenticated users can access protected endpoints.
    - Admin privileges are required for user deletion and management operations.

## Testing

To execute unit tests:

```bash
mvn test
```

## Future Enhancements

- **OAuth2 integration** for social logins (Google, Facebook).
- **Audit logging** for tracking user activities.
