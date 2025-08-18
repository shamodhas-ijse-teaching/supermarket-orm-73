# 🛒 Supermarket System – ORM Layered Architecture (GDSE-73)

[![Java](https://img.shields.io/badge/Java-17+-brightgreen?logo=java)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-UI-blue?logo=java)](https://openjfx.io/)
[![Hibernate](https://img.shields.io/badge/Hibernate-ORM-orange?logo=hibernate)]
[![MySQL](https://img.shields.io/badge/Database-MySQL-blue?logo=mysql)]
[![IJSE GDSE](https://img.shields.io/badge/IJSE-GDSE--73-purple)](https://ijse.lk)

A JavaFX desktop application demonstrating **Object-Oriented Mapping (ORM)** using **Hibernate** in a **Layered Architecture**.  
Developed as part of the **GDSE-73** course at IJSE.

---

## 📌 Module: ORM with Hibernate

This module implements **Customer Management** with Hibernate ORM — replacing JDBC and emphasizing clean separation between layers.

---

## 🧱 Architecture Overview

This system follows a strict **Layered + MVC** architecture pattern:

---

## 📋 Features

* **Customer Management:** Add, update, delete, and view customer details.
* **Item Management:** Manage product inventory. *(Planned)*
* **Order Processing:** Create and manage customer orders. *(Planned)*

---

## 🏛️ Architecture

This project strictly follows a layered architecture to separate concerns and improve maintainability.

* **Controller Layer:** Manages the UI (JavaFX) and user interactions.
* **Service (BO) Layer:** Handles all business logic and orchestrates data flow.
* **DAO Layer:** Responsible for all data access operations, abstracting the database from the rest of the application.
* **DTO Layer:** Data Transfer Objects used to carry data between layers.
* **Factory Pattern:** A `DAOFactory` is used to provide the necessary DAO implementations to the service layer, promoting loose coupling.

---

## 🛠️ Technologies Used

| Technology      | Description                          |
|-----------------|--------------------------------------|
| Java 17+        | Core language                        |
| JavaFX          | UI Framework                         |
| Hibernate ORM   | Database persistence layer           |
| MySQL           | Relational Database                  |
| Maven / Gradle  | Dependency & Build Management        |
| Git & GitHub    | Version Control                      |

---

## 🚀 How to Run

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/shamodhas-ijse-teaching/supermarket-layered-architecture-ad1-gdse-74.git](https://github.com/shamodhas-ijse-teaching/supermarket-layered-architecture-ad1-gdse-74.git)
    ```
2.  **Database Setup:** Import the provided `.sql` file into your MySQL database to create the necessary tables.
3.  **Configure DB Connection:** Update the database username and password in the configuration file.
4.  **Build & Run:** Open the project in your IDE (e.g., IntelliJ IDEA) and run the `AppInitializer.java` file.
