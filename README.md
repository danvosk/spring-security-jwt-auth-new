# 🔐 Spring Boot JWT Role-Based Authentication API

This project is a secure backend application built with Spring Boot that uses **JWT (JSON Web Token)** for authentication and **role-based authorization (RBAC)**.

## 🚀 Features

- User registration and login
- JWT token generation and validation
- Role-based access control (`USER`, `TRAINER`, `ADMIN`)
- HTTP method-level permission management
- In-memory H2 database for testing
- BCrypt password encryption
- Stateless authentication with Spring Security filters

## 🧰 Tech Stack

- Java 21
- Spring Boot 3.x
- Spring Security
- JWT (JJWT library)
- H2 Database
- Maven
- Lombok

## 🔐 Roles and Permissions

| HTTP Method | Endpoint          | USER | TRAINER | ADMIN |
|-------------|-------------------|------|---------|--------|
| GET         | `/api/read`       | ✅    | ✅       | ✅      |
| POST        | `/api/create`     | ❌    | ✅       | ✅      |
| PUT         | `/api/update`     | ❌    | ❌       | ✅      |
| DELETE      | `/api/delete`     | ❌    | ❌       | ✅      |

## 📦 API Endpoints

### 🔸 Authentication

- `POST /auth/register`: Register a new user
- `POST /auth/login`: Login and receive a JWT token

### 🔸 Test API

- `GET /api/read`: Accessible by all roles
- `POST /api/create`: Accessible by TRAINER and ADMIN
- `PUT /api/update`: Accessible by ADMIN only
- `DELETE /api/delete`: Accessible by ADMIN only
