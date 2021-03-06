package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    /**
     * Database connection credentials
     */
    private static final String URL = "jdbc:postgresql://database-1.c5mz1ul4etaa.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=banking_app";
    private static final String USERNAME = System.getenv("db_username");
    private static final String PASSWORD = System.getenv("db_password");

    /**
     * Establishes connection object
     */
    private static Connection connection;

    /**
     * Method for connecting to the database
     * @return a connection to the database
     */
    public static Connection connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }
}
