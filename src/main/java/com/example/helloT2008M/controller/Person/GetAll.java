package com.example.helloT2008M.controller.Person;

import com.example.helloT2008M.entity.Person;
import com.example.helloT2008M.model.PersonModel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAll extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Person person = new Person();
        PersonModel personModel = new PersonModel();
        personModel.getAll(person);

    }
}
