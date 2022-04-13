package com.example.helloT2008M.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static Connection connection;
    public static String connectString = "jdbc:mysql://localhost:3306/fdata";
    public static String userName = "root";
    public static String password = "";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        if (connection == null) {
            connection = DriverManager.getConnection(connectString, userName, password);
        } else {
            System.out.println("Use existing connection.");
        }

        return connection;
    }

}
