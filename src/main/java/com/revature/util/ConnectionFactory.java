package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://database-1.c5mz1ul4etaa.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=banking_app";
    private static final String USERNAME = "jared";
    private static final String PASSWORD = "p4ssw0rd";

    private static Connection connection;

    public static Connection connect() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("DB CONNECTED");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }
}
