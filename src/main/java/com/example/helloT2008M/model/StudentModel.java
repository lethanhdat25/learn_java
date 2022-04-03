package com.example.helloT2008M.model;

import com.example.helloT2008M.entity.Student;
import com.example.helloT2008M.helper.Connector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    private Statement statement;
    public StudentModel(){
        Connector connector=new Connector();
        try {
            Connection connection = connector.getConnection();
            statement= connection.createStatement();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void save(Student student) throws SQLException, ClassNotFoundException {
        String sqlStatement =
                String.format("insert into students (name, email, roll) values ('%s', '%s', '%s')",
                        student.getName(), student.getEmail(), student.getRoll());
        statement.execute(sqlStatement);
    }
    public List<Student> getStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sqlStatement = "select * form students";
        ResultSet rs=statement.executeQuery(sqlStatement);
        while (rs.next()){
            String name = rs.getString("name");
            String email = rs.getString("email");
            String roll = rs.getString("roll");
            Student student = new Student(name,email,roll);
            students.add(student);
        }
        return students;
    }
}

