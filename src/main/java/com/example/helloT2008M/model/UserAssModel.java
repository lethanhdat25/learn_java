package com.example.helloT2008M.model;

import com.example.helloT2008M.Repository.MyRepository;
import com.example.helloT2008M.entity.Account;
import com.example.helloT2008M.entity.UserAss;

import java.sql.ResultSet;

public class UserAssModel {

    public UserAssModel(){

    }

    MyRepository<UserAss> myRepository = new MyRepository<>();

    public UserAss findUserByName(UserAss account, String name){
        UserAss resUser = null;
        try{
            ResultSet rs = myRepository.findByName(account, name);
            while (rs.next()){
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                resUser = new UserAss(userName, password);
            }
        }catch (Exception e){
            return null;
        }
        return resUser;
    }
}
