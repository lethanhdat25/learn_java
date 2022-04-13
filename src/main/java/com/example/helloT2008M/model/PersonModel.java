package com.example.helloT2008M.model;

import com.example.helloT2008M.Repository.MyRepository;
import com.example.helloT2008M.entity.Account;
import com.example.helloT2008M.entity.Person;
import com.example.helloT2008M.entity.Product;

import java.sql.ResultSet;

public class PersonModel {

    public PersonModel() {
    }

    public void getAll(Person person){
        MyRepository<Person> myRepository = new MyRepository<>();
        myRepository.getAll(person);
    }

    public void save(Person person) throws InstantiationException, IllegalAccessException {
        MyRepository<Person> myRepository=new MyRepository<>();
        myRepository.save(person);
    }

    public void update(Person person, int id) throws IllegalAccessException {
        MyRepository<Person> myRepository = new MyRepository<>();
        myRepository.update(person, id);
    }

    public void delete(Person person, int id){
        MyRepository<Person> myRepository = new MyRepository<>();
        myRepository.delete(person, id);
    }

}
