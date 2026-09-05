package com.sunrise.dental.util;

import java.sql.Connection;

public class DatabaseConnectionTest {

    public static void main(String[] args) {

        try {
            Connection connection = DatabaseConnection.getConnection();

            if (connection != null) {
                System.out.println("Database connection successful!");
            }

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }
}