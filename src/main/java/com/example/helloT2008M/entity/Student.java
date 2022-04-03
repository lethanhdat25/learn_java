package com.example.helloT2008M.entity;

import com.example.helloT2008M.annotation.Table;

//@Table(name = "Students")
public class Student {
    public String roll;
    public String name;
    public String email;

    public Student() {
    }

    public Student(String name, String email, String roll) {
        this.roll = roll;
        this.name = name;
        this.email = email;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
