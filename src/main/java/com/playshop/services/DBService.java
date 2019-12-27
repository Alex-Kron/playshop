package com.playshop.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public interface DBService {
    static final String url = "jdbc:mysql://127.0.0.1:3306/playshop";
    static final String dbUser = "root";
    static final String dbPassword = "Ds[e[jkm";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, dbUser, dbPassword);
    }

    public static Statement getStatement() throws SQLException {
        return DriverManager.getConnection(url, dbUser, dbPassword).createStatement();
    }
}
