package com.example.helloT2008M.Repository;

import com.example.helloT2008M.annotation.Column;
import com.example.helloT2008M.annotation.Table;
import com.example.helloT2008M.helper.ExecuteStatement;
import com.example.helloT2008M.ultil.Constants.Constants;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MyRepository<T> {

    public ResultSet getAll(T obj){
        Class clazz = obj.getClass();
        String tableName = "";

        if(!clazz.isAnnotationPresent(Table.class)){
            return null;
        }

        Table table = (Table) clazz.getDeclaredAnnotation(Table.class);

        if (table.name() != null && table.name().length() > 0){
            tableName = table.name();
        }else{
            tableName = clazz.getSimpleName() + "s";
        }

        //TODO: Get All Statement
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.SELECT);
        stringBuilder.append(Constants.ALL);
        stringBuilder.append(Constants.FROM);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(tableName);

        //TODO: Execute Statement
        System.out.println(stringBuilder);
        return ExecuteStatement.ExecuteQuery(stringBuilder);
    }

    public void save(T obj) throws InstantiationException, IllegalAccessException {
        String tableName = "";
        Class clazz = obj.getClass();

        if (!clazz.isAnnotationPresent(Table.class)) {
            return;
        }

        Table table = (Table) clazz.getDeclaredAnnotation(Table.class);

        if (table.name() != null && table.name().length() > 0) {
            tableName = table.name();
        } else {
            tableName = clazz.getSimpleName() + "s";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.INSERT);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(tableName);

        //get FIELD

        StringBuilder fieldNameBuilder = new StringBuilder();
        fieldNameBuilder.append(Constants.ROUND_BRACKETS_OPEN);
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Column.class)) {
                continue;
            }

            Column column = field.getDeclaredAnnotation(Column.class);

            if (!column.isCreate()){
                continue;
            }

            fieldNameBuilder.append(column.name());
            fieldNameBuilder.append(Constants.COMMA);
        }

        fieldNameBuilder.setLength(fieldNameBuilder.length() - Constants.COMMA.length());
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

        for (Field field : fields) {
            try {
                if (!field.isAnnotationPresent(Column.class)) {
                    continue;
                }
                Column column = field.getDeclaredAnnotation(Column.class);
                field.setAccessible(true);
                if (!column.isCreate()){
                    continue;
                }
                if (column.type().contains(Constants.VARCHAR250) || column.type().contains(Constants.VARCHAR150) || column.type().contains((Constants.TEXT)) || column.type().contains(Constants.DATETIME)) {
                    fieldValueBuilder.append(Constants.SPACE);
                    fieldValueBuilder.append(Constants.APOSTROPHE);
                    fieldValueBuilder.append(field.get(obj));
                    fieldValueBuilder.append(Constants.APOSTROPHE);
                } else {
                    fieldValueBuilder.append(field.get(obj));
                }
                fieldValueBuilder.append(Constants.COMMA);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        fieldValueBuilder.setLength(fieldValueBuilder.length() - Constants.COMMA.length());
        fieldValueBuilder.append(Constants.ROUND_BRACKETS_CLOSE);
        stringBuilder.append(fieldValueBuilder);

        //TODO: Done SQL statement: (...value)

        //TODO: Execute statement
        System.out.println(stringBuilder);
        ExecuteStatement.Execute(stringBuilder);
        clazz.newInstance();
    }

    public void update(T obj, int id) {
        String tableName = "";
        System.out.println("update in stories");

        Class clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) {
            return;
        }

        Table table = (Table) clazz.getDeclaredAnnotation(Table.class);

        if (table.name() != null && table.name().length() > 0) {
            tableName = table.name();
        } else {
            tableName = clazz.getSimpleName() + "s";
        }

        //TODO: Statement UPDATE table_name SET
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.UPDATE);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(tableName);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(Constants.SET);
        stringBuilder.append(Constants.SPACE);

        StringBuilder valueStringBuilder = new StringBuilder();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {

                if (!field.isAnnotationPresent(Column.class)) {
                    continue;
                }

                Column column = field.getDeclaredAnnotation(Column.class);

                if (!column.isCreate()){
                    continue;
                }

                field.setAccessible(true);
                valueStringBuilder.append(column.name());
                valueStringBuilder.append(Constants.EQUAL);

                if (column.type().contains(Constants.VARCHAR150) || column.type().contains(Constants.TEXT)  || column.type().contains(Constants.DATETIME)){
                    valueStringBuilder.append(Constants.APOSTROPHE);
                    valueStringBuilder.append(field.get(obj));
                    valueStringBuilder.append(Constants.APOSTROPHE);
                }else {
                    valueStringBuilder.append(field.get(obj));
                }

                valueStringBuilder.append(Constants.COMMA);

            }catch (IllegalAccessException e){
                System.out.println(e.getMessage());
            }
        }

        valueStringBuilder.setLength(valueStringBuilder.length()-2);
        stringBuilder.append(valueStringBuilder);

        //TODO: Add Condition
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(Constants.WHERE);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(Constants.ID);
        stringBuilder.append(Constants.EQUAL);
        stringBuilder.append(id);

        //TODO: Execute statement

        System.out.println(stringBuilder);
        ExecuteStatement.Execute(stringBuilder);

    }

    public void delete(T obj, int id){
        Class clazz = obj.getClass();
        String tableName = "";
                ;
        if (!clazz.isAnnotationPresent(Table.class)){
            return;
        }

        Table table = (Table) clazz.getDeclaredAnnotation(Table.class);

        if (table.name() != null && table.name().length() > 0) {
            tableName = table.name();
        }else{
            tableName = clazz.getSimpleName() + "s";
        }


        //TODO: Delete Statement
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.DELETE);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(Constants.FROM);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(tableName);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(Constants.WHERE);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(Constants.ID);
        stringBuilder.append(Constants.EQUAL);
        stringBuilder.append(id);

        //TODO: Execute Statement
        ExecuteStatement.Execute(stringBuilder);

    }

    public ResultSet findByName(T obj, String name) throws SQLException {
        Class clazz = obj.getClass();
        String tableName = "";
        final String QUERY = "userName";

        if (!clazz.isAnnotationPresent(Table.class)){
            return null;
        }

        Table table = (Table) clazz.getDeclaredAnnotation(Table.class);

        if (table.name() != null && table.name().length()>0){
            tableName =  table.name();
        }else{
            tableName = clazz.getSimpleName() + "s";
        }

        //TODO: Statement Find By Name
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.SELECT);
        stringBuilder.append(Constants.ALL);
        stringBuilder.append(Constants.FROM);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(tableName);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(Constants.WHERE);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(QUERY);
        stringBuilder.append(Constants.EQUAL);
        stringBuilder.append(Constants.APOSTROPHE);
        stringBuilder.append(name);
        stringBuilder.append(Constants.APOSTROPHE);

        //TODO: Execute Statement
        return ExecuteStatement.ExecuteQuery(stringBuilder);


    }
    public ResultSet findById(T obj, int id) throws SQLException {
        Class clazz = obj.getClass();
        String tableName = "";
        final String QUERY = "id";

        if (!clazz.isAnnotationPresent(Table.class)){
            return null;
        }

        Table table = (Table) clazz.getDeclaredAnnotation(Table.class);

        if (table.name() != null && table.name().length()>0){
            tableName =  table.name();
        }else{
            tableName = clazz.getSimpleName() + "s";
        }

        //TODO: Statement Find By Id
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.SELECT);
        stringBuilder.append(Constants.ALL);
        stringBuilder.append(Constants.FROM);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(tableName);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(Constants.WHERE);
        stringBuilder.append(Constants.SPACE);
        stringBuilder.append(QUERY);
        stringBuilder.append(Constants.EQUAL);
        stringBuilder.append(Constants.APOSTROPHE);
        stringBuilder.append(id);
        stringBuilder.append(Constants.APOSTROPHE);

        //TODO: Execute Statement
        return ExecuteStatement.ExecuteQuery(stringBuilder);


    }

}
