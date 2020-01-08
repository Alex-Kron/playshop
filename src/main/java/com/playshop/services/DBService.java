package com.playshop.services;

import com.sun.tools.javac.code.Attribute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBService {
    static final String url = "jdbc:mysql://127.0.0.1:3306/playshop";
    static final String urlTest = "jdbc:mysql://127.0.0.1:3306/playshop_test";
    static final String dbUser = "root";
    static final String dbPassword = "Ds[e[jkm";
    static Connection connection = setConnection();
    static Connection testConnection = setTestConnection();


    private static Connection setConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Connection setTestConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(urlTest, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Connection getTestConnection() {
        return testConnection;
    }
}
