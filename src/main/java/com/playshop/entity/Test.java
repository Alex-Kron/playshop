package com.playshop.entity;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/playshop", "root","Ds[e[jkm");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null) {
            System.out.println("Connection completed");
        } else {
            System.out.println("Connection failed");
        }
    }
}
