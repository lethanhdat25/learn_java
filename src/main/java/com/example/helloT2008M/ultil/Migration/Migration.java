package com.example.helloT2008M.ultil.Migration;

import com.example.helloT2008M.annotation.Column;
import com.example.helloT2008M.annotation.Table;
import com.example.helloT2008M.helper.Connector;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class Migration {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("com.example.helloT2008M");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Table.class);
        for (Class<?> table : annotated) {
            createTableFromEntity(table);
        }
    }

    public static void createTableFromEntity(Class clazz){
        if(!clazz.isAnnotationPresent(Table.class)){
            return;
        }
        StringBuilder sqlStringBuilder= new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE");
        sqlStringBuilder.append(" ");
        String tableName  = clazz.getSimpleName().toLowerCase() + "s";
        Table table =(Table) clazz.getDeclaredAnnotation(Table.class);
        if (table.name() != null && table.name().length()>0){
            tableName=table.name();
        }
        sqlStringBuilder.append(tableName);
        sqlStringBuilder.append(" ");
        sqlStringBuilder.append("(");

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String columnName = field.getName();
            String columnType = "";
            if (field.getType().getSimpleName().contains("String")) {
                columnType = "VARCHAR(250)";
            } else {
                columnType = field.getType().getSimpleName();
            }
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getDeclaredAnnotation(Column.class);
                columnName = column.name();
                columnType = column.type();
            }
            sqlStringBuilder.append(columnName);
            sqlStringBuilder.append(" ");
            sqlStringBuilder.append(columnType);
            sqlStringBuilder.append(", ");
        }
        sqlStringBuilder.setLength(sqlStringBuilder.length() - 2);
        sqlStringBuilder.append(")");

        try {
            Connection connection = Connector.getConnection();
            Statement dropStatement = connection.createStatement();
            dropStatement.execute(String.format("DROP TABLE %s", tableName));
            System.out.println(String.format("DROP TABLE %s success ", tableName));
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            Connection connection = Connector.getConnection();
            Statement stt = connection.createStatement();
            stt.execute(sqlStringBuilder.toString());
            System.out.println(String.format("CREATE TABLE %s success ", tableName));
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }


}
