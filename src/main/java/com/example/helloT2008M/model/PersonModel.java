package com.example.helloT2008M.model;

import com.example.helloT2008M.Repository.MyRepository;
import com.example.helloT2008M.entity.Person;
import com.example.helloT2008M.helper.Connector;

import java.sql.Connection;
import java.sql.Statement;

public class PersonModel {

    public PersonModel() {
    }
    public void save(Person person) throws InstantiationException, IllegalAccessException {
        MyRepository<Person> myRepository=new MyRepository<>();
        myRepository.save(person);
    }
}
