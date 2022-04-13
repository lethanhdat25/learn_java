package com.example.helloT2008M.controller.Person;
import com.example.helloT2008M.entity.Person;
import com.example.helloT2008M.model.PersonModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class Update extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        int id = Integer.parseInt(req.getParameter("id"));

        Person person = new Person(name, age);
        PersonModel personModel = new PersonModel();

        try {
            personModel.update(person, id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
