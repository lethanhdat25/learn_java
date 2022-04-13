package com.example.helloT2008M.model;

import com.example.helloT2008M.Repository.MyRepository;
import com.example.helloT2008M.entity.Account;
import com.example.helloT2008M.helper.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountModel {
    public AccountModel(){

    }

    MyRepository<Account> myRepository = new MyRepository<>();

    public boolean save(Account account){
        try {
            myRepository.save(account);
            return true;

        }catch (Exception e){
            return false;
        }
    }

    public Account findAccountByUserName(Account account,String name){
        Account resAccount = null;
        try{
           ResultSet rs = myRepository.findByName(account, name);
           while (rs.next()){
               int id = rs.getInt("Id");
               String userName = rs.getString("userName");
               String fullName = rs.getString("fullName");
               String salt = rs.getString("salt");
               String createAt = rs.getString("createAt");
               String lockTime = rs.getString("lockTime");
               int status = rs.getInt("status");
               resAccount = new Account(id, userName, fullName, salt, createAt, lockTime, status);
           }
        }catch (Exception e){
            return null;
        }
        return resAccount;
    }
    public boolean updateLock(String username, Account account) {
        try {
            Connection connection = Connector.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(
                            "INsert into ? ? ? ? ");
            statement.setInt(1, account.getStatus());
            statement.setInt(2, account.getFailureCount());
            statement.setString(3, account.getLockTime().toString());
            statement.setString(4, username);
            statement.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
