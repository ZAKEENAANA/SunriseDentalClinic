package com.sunrise.dental.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            System.getenv("SUNRISE_DB_URL");

    private static final String USER =
            System.getenv("SUNRISE_DB_USER");

    private static final String PASSWORD =
            System.getenv("SUNRISE_DB_PASSWORD");

    public static Connection getConnection() throws SQLException {

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            throw new SQLException(
                    "MySQL JDBC Driver not found!",
                    e
            );
        }

        // Check database configuration
        if (URL == null || USER == null || PASSWORD == null) {

            throw new SQLException(
                    "Database environment variables are not configured."
            );
        }

        // Create and return database connection
        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}