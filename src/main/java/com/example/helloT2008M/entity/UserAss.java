package com.example.helloT2008M.entity;

import com.example.helloT2008M.annotation.Column;
import com.example.helloT2008M.annotation.Id;
import com.example.helloT2008M.annotation.Table;
import com.example.helloT2008M.ultil.Constants.Constants;

@Table(name = "tbUser")
public class UserAss {
    @Id(name = "id", type = Constants.INT, isAutoIncrement = true, isPrimaryKey = true)
    public int id;
    @Column(name = "username", type = Constants.VARCHAR150)
    public String  username;
    @Column(name = "password", type = Constants.VARCHAR150)
    public  String password;

    public UserAss() {
    }

    public UserAss(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
