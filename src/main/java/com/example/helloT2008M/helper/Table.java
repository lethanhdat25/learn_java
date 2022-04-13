package com.example.helloT2008M.helper;

import com.example.helloT2008M.ultil.Constants.Constants;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {
    public static void createTable(String sqlStringBuilder, String tableName) {
        final String TABLE_EXISTS = Constants.TABLE + Constants.SPACE + Constants.APOSTROPHE + tableName.toUpperCase() +
                Constants.APOSTROPHE +  Constants.SPACE + Constants.ALREADY_EXISTS;
        try {
            Connection connection = Connector.getConnection();
            Statement stt = connection.createStatement();
            stt.execute(sqlStringBuilder);
            System.out.printf("CREATE TABLE %s success %n", tableName);
        } catch (SQLException | ClassNotFoundException e) {
            if (e.getMessage().toUpperCase().equals(TABLE_EXISTS)) {
                dropTable(tableName);
                createTable(sqlStringBuilder, tableName);
            }else{
                System.err.println(e.getMessage().toUpperCase());
            }
        }
    }

    public static void dropTable(String tableName) {
        try {
            Connection connection = Connector.getConnection();
            Statement dropStatement = connection.createStatement();
            dropStatement.execute(String.format("DROP TABLE %s", tableName));
            System.out.printf("DROP TABLE %s success %n", tableName);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
