# Secure Notes App

A simple **JavaFX-based Secure Notes App** with a **MySQL database** for storing notes. Users can **add, edit, and delete** notes, and all operations are stored securely in the database.

## Features
- **Add Notes**: Users can create new notes.
- **Edit Notes**: Users can modify existing notes.
- **Delete Notes**: Users can remove notes.
- **MySQL Database Integration**: All notes are stored persistently in a MySQL database.
- **Simple UI**: JavaFX-based user interface with an easy-to-use design.

## Technologies Used
- **Java** (JDK 8 or later)
- **JavaFX** (For UI)
- **FXML** (For UI layout)
- **MySQL** (For database storage)
- **JDBC** (For database connection)

## Setup Instructions

### 1. Install Required Software
- Download and install **Java (JDK 8+)**.
- Download and install **MySQL Server**.
- Ensure **JavaFX is configured** in your development environment.

### 2. Create MySQL Database
Run the following SQL commands in MySQL to create the database and table:

```sql
CREATE DATABASE simplenotesdb;
USE simplenotesdb;

CREATE TABLE notes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL
);
