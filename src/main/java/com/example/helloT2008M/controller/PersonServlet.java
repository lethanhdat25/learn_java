package com.example.helloT2008M.controller;

import com.example.helloT2008M.entity.Person;
import com.example.helloT2008M.model.PersonModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        Person person = new Person(name, age);
        PersonModel personModel = new PersonModel();
        try {
            personModel.save(person);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
