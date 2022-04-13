package com.example.helloT2008M.entity;

import com.example.helloT2008M.annotation.Column;
import com.example.helloT2008M.annotation.Id;
import com.example.helloT2008M.annotation.Table;
import com.example.helloT2008M.ultil.Constants.Constants;

import java.time.LocalDateTime;

@Table(name = "Accounts")
public class Account {
    @Id(name = "Id", type = "int", isPrimaryKey = true, isAutoIncrement = true)
    public int Id;
    @Column(name = "userName",type = Constants.VARCHAR150)
    public String userName;

    @Column(name = "fullName",type = Constants.VARCHAR150)
    public String fullName;

    @Column(name = "password",type = Constants.VARCHAR150)
    public String password;

    @Column(name = "salt",type = Constants.VARCHAR150)
    public String salt;

    @Column(name = "createAt",type = Constants.VARCHAR150)
    public String createAt;

    @Column(name = "failureCount",type = Constants.VARCHAR150)
    public int failureCount;

    @Column(name = "lockTime",type = Constants.DATETIME)
    private LocalDateTime lockTime;

    @Column(name = "status",type = Constants.INT)
    private int status;

    public Account() {
    }

    public Account(int Id,String userName, String fullName, String password, String salt, String createAt, int status) {
        this.Id = Id;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.salt = salt;
        this.createAt = createAt;
        this.status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    public LocalDateTime getLockTime() {
        return lockTime;
    }

    public void setLockTime(LocalDateTime lockTime) {
        this.lockTime = lockTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
