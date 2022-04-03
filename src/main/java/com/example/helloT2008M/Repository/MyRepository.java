package com.example.helloT2008M.Repository;

import com.example.helloT2008M.annotation.Column;
import com.example.helloT2008M.annotation.Table;
import com.example.helloT2008M.helper.Connector;
import com.example.helloT2008M.ultil.Constants.Constants;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;

public class MyRepository<T> {
    public T save(T obj) throws InstantiationException, IllegalAccessException {
        String tableName = "";

        Class clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) {
            return null;
        }
        Table table = (Table) clazz.getDeclaredAnnotation(Table.class);
        if (table.name() != null && table.name().length() > 0) {
            tableName =table.name();
        }else {
            tableName = clazz.getSimpleName() + "s";
        }
        StringBuilder stringBuilder =new StringBuilder();
        stringBuilder.append(Constants.INSERT);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(tableName);

        //get FIELD

        StringBuilder fieldNameBuilder = new StringBuilder();
        fieldNameBuilder.append(Constants.ROUND_BRACKETS_OPEN);
        Field[] fields = clazz.getDeclaredFields();
        for(Field field: fields){
            if (!field.isAnnotationPresent(Column.class)){
                continue;
            }
            Column column = field.getDeclaredAnnotation(Column.class);
            fieldNameBuilder.append(column.name());
            fieldNameBuilder.append(Constants.COMMA);
        }
        fieldNameBuilder.setLength(fieldNameBuilder.length()-Constants.COMMA.length());
        fieldNameBuilder.append(Constants.ROUND_BRACKETS_CLOSE);
        fieldNameBuilder.append(Constants.SPACE);
        stringBuilder.append(fieldNameBuilder);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(Constants.VALUES);
        stringBuilder.append(Constants.SPACE);


        //TODO: DONE sql statement : insert into table_name(...column) values

        //TODO: Continue!!
        StringBuilder fieldValueBuilder = new StringBuilder();
        fieldValueBuilder.append(Constants.ROUND_BRACKETS_OPEN);
        for (Field field : fields){
            try {
                if(!field.isAnnotationPresent(Column.class)){
                    continue;
                }
                Column column = field.getDeclaredAnnotation(Column.class);
                field.setAccessible(true);
                if (column.type().contains(Constants.VARCHAR150) || column.type().contains((Constants.TEXT))){
                    fieldValueBuilder.append(Constants.SPACE);
                    fieldValueBuilder.append(Constants.APOSTROPHE);
                    fieldValueBuilder.append(field.get(obj));
                    System.out.println(field.get(obj));
                    fieldValueBuilder.append(Constants.APOSTROPHE);
                }else {
                    fieldValueBuilder.append(field.get(obj));
                }
                fieldValueBuilder.append(Constants.COMMA);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        fieldValueBuilder.setLength(fieldValueBuilder.length()-Constants.COMMA.length());
        fieldValueBuilder.append(Constants.ROUND_BRACKETS_CLOSE);
        stringBuilder.append(fieldValueBuilder);

        //TODO: Done SQL statement: (...value)

        //TODO: Execute statement
        try {
            Connection connection = Connector.getConnection();
            Statement statement =  connection.createStatement();
            statement.execute(stringBuilder.toString());
            System.out.println("Action Success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return (T) clazz.newInstance();
    }
}
