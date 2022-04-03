package com.example.helloT2008M.controller;

import com.example.helloT2008M.entity.Student;
import com.example.helloT2008M.model.StudentModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            StudentModel studentModel=new StudentModel();
            List<Student> students=studentModel.getStudents();
            req.setAttribute("listStudent",students);
            System.out.println(students);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
