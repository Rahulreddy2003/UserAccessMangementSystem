This file guides reviewers or users on how to set up and run the project.

# User Access Management System

A simple Java web application to manage user access requests using Servlets, JSP, and PostgreSQL.

## Features
- User registration and login
- Software creation (Admins only)
- Access request submission (Employees only)
- Approval or rejection of requests (Managers only)
- PostgreSQL-backed data storage

## Technologies
- Java Servlets & JSP
- Apache Tomcat
- PostgreSQL
- Maven

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/UserAccessManagementSystem.git
cd UserAccessManagementSystem

### 2. Database Setup

Create a PostgreSQL database named uams.

Run the SQL script in database/schema.sql to create the required tables.


### 3. Configure Database

Edit DBUtil.java and update:

private static final String URL = "jdbc:postgresql://localhost:5432/uams";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";

### 4. Build the Project

mvn clean install

### 5. Deploy on Apache Tomcat

Deploy the generated WAR file (target/uams.war) to your Tomcat webapps directory.

Start Tomcat and access the application at http://localhost:8080/uams/.


Roles

Employee: Sign up and request access

Manager: Approve/reject requests

Admin: Add software and perform all actions


License

This project is for educational purposes only.
