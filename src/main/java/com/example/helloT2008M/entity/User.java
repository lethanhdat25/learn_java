package com.example.helloT2008M.entity;

import com.example.helloT2008M.annotation.Column;
import com.example.helloT2008M.annotation.Id;
import com.example.helloT2008M.annotation.Table;
import com.example.helloT2008M.ultil.Constants.Constants;

public class User {
    @Id(name= Constants.ID, type = Constants.INT, isPrimaryKey = true, isAutoIncrement = true)
    public int Id;

    @Column(name="Name", type = Constants.VARCHAR150)
    public String Name;

    @Column(name="Password", type = Constants.VARCHAR150)
    public String Password;

    public User() {
    }

    public User(String name, String password) {
        Name = name;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
