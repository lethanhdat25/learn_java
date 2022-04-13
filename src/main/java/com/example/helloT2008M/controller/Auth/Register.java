package com.example.helloT2008M.controller.Auth;

import com.example.helloT2008M.entity.Account;
import com.example.helloT2008M.helper.PasswordHandler;
import com.example.helloT2008M.model.AccountModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class Register extends HttpServlet {
    AccountModel accountModel = new AccountModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String fullName = req.getParameter("fullname");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("conf irmPassword");

        Account account = new Account();

        account.setUserName(userName);
        account.setFullName(fullName);
        String salt = PasswordHandler.generateSalt();
        account.setSalt(salt);
        String passwordHash = PasswordHandler.getMD5(password, salt);
        account.setPassword(passwordHash);
        account.setStatus(1);
        account.setCreateAt(Calendar.getInstance().getTime().toString());
        boolean result = accountModel.save(account);
        if (result) {
            resp.getWriter().println("Register success!");
        } else {
            resp.getWriter().println("Error occurs, please try again.");
        }

    }
}
