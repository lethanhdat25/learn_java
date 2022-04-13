package com.example.helloT2008M.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExecuteStatement {
    public static void Execute(StringBuilder stringBuilder){
        //TODO: Execute statement
        try {
            Connection connection = Connector.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(stringBuilder.toString());
            System.out.println("Action Success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet ExecuteQuery(StringBuilder stringBuilder){
        ResultSet resultSet = null;
        //TODO: Execute statement
        try {
            Connection connection = Connector.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(stringBuilder.toString());
            System.out.println("Action Success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return resultSet;
    }

}
