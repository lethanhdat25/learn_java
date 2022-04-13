package com.example.helloT2008M.ultil.Migration;

import com.example.helloT2008M.annotation.Column;
import com.example.helloT2008M.annotation.Id;
import com.example.helloT2008M.helper.Table;
import com.example.helloT2008M.ultil.Constants.Constants;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Set;


public class Migration {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("com.example.helloT2008M");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(com.example.helloT2008M.annotation.Table.class);

        for (Class<?> table : annotated) {
            createTableFromEntity(table);
        }
    }

    public static void createTableFromEntity(Class clazz) {
        if (!clazz.isAnnotationPresent(com.example.helloT2008M.annotation.Table.class)) {
            return;
        }

        StringBuilder sqlStringBuilder = new StringBuilder();
        StringBuilder sqlId = new StringBuilder();
        StringBuilder sqlPrimaryKey = new StringBuilder();
        String tableName = clazz.getSimpleName().toLowerCase() + "s";
        Field[] fields = clazz.getDeclaredFields();
        com.example.helloT2008M.annotation.Table table = (com.example.helloT2008M.annotation.Table) clazz.getDeclaredAnnotation(com.example.helloT2008M.annotation.Table.class);

        sqlStringBuilder.append(Constants.CREATE_TABLE);
        sqlStringBuilder.append(Constants.SPACE);

        if (table.name() != null && table.name().length() > 0) {
            tableName = table.name();
        }

        sqlStringBuilder.append(tableName);
        sqlStringBuilder.append(Constants.SPACE);
        sqlStringBuilder.append(Constants.ROUND_BRACKETS_OPEN);


        for (Field field : fields) {

            String columnName = field.getName();
            String columnType = "";

            if (field.getType().getSimpleName().contains("String")) {
                columnType = Constants.VARCHAR250;
            } else {
                columnType = field.getType().getSimpleName();
            }

            if (field.isAnnotationPresent(Id.class)) {
                columnName= "";
                columnType= "";
                Id id = field.getDeclaredAnnotation(Id.class);
                sqlId.append(id.name());
                sqlId.append(Constants.SPACE);
                sqlId.append(id.type());
                sqlId.append(Constants.SPACE);

                if (id.isPrimaryKey()) sqlPrimaryKey.append(Constants.PRIMARY_KEY);
                if (id.isAutoIncrement()) sqlId.append(Constants.AUTO_INCREMENT);
            }else{
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getDeclaredAnnotation(Column.class);
                    if(!column.isCreate()){
                        return;
                    }
                    columnName = column.name();
                    columnType = column.type();
                }
            }


            if (sqlId.length() != 0) {
                sqlStringBuilder.append(sqlId);
                sqlId.setLength(0);
                sqlStringBuilder.append(Constants.COMMA);
            }else {
                sqlStringBuilder.append(columnName);
                sqlStringBuilder.append(Constants.SPACE);
                sqlStringBuilder.append(columnType);
                sqlStringBuilder.append(Constants.COMMA);
            }
        }

        if (sqlPrimaryKey.length() != 0) {
            sqlStringBuilder.append(sqlPrimaryKey);
        }else {
            sqlStringBuilder.setLength(sqlStringBuilder.length()-2);
        }

        sqlStringBuilder.append(Constants.ROUND_BRACKETS_CLOSE);
        System.out.println(sqlStringBuilder);
        
        Table.createTable(sqlStringBuilder.toString(), tableName);
    }


}
