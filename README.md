# Lodge Admin Panel

## Overview
A simple Java Swing-based Lodge Admin Panel for managing room availability, customer check-ins/check-outs, and billing. Uses JDBC for database integration with SQL Server.

## Features
- Room availability tracking
- Customer check-in/out
- Automated bill generation
- SQL Server database integration

## Setup
1. Clone the repo:
    ```bash
    git clone https://github.com/naveenpb/Lodge-Admin-Pannel
    cd Project2
    ```
2. Configure the `hotelmanagement` database in SQL Server.
3. Update connection settings in `DatabaseConnection.java`.
4. Compile and run:
    ```bash
    javac src/com/lodge/AdminPanel.java
    java src/com/lodge/AdminPanel
    ```
