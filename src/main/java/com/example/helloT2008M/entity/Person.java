package com.example.helloT2008M.entity;

import com.example.helloT2008M.annotation.Column;
import com.example.helloT2008M.annotation.Id;
import com.example.helloT2008M.annotation.Table;
import com.example.helloT2008M.ultil.Constants.Constants;

import java.io.File;

@Table(name = "Persons")
public class Person {
    @Id(name=Constants.ID, type = Constants.INT, isPrimaryKey = true, isAutoIncrement = true)
    public int id;

    @Column(name="Name", type = Constants.VARCHAR150)
    public String name;

    @Column(name = "Age", type=Constants.INT)
    public int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
