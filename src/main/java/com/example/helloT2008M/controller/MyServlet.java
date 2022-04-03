package com.example.helloT2008M.controller;

import com.example.helloT2008M.entity.Student;
import com.example.helloT2008M.model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String roll=req.getParameter("roll");
        String email=req.getParameter("email");
        Student student=new Student(name,email,roll);
        StudentModel studentModel=new StudentModel();
        try {
            studentModel.save(student);
            resp.getWriter().println("thanh cong");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
