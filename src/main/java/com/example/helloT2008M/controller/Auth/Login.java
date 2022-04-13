package com.example.helloT2008M.controller.Auth;

import com.example.helloT2008M.Main;
import com.example.helloT2008M.entity.Account;
import com.example.helloT2008M.helper.PasswordHandler;
import com.example.helloT2008M.model.AccountModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class Login  extends HttpServlet {
    AccountModel accountModel = new AccountModel();
    private static final int MAX_COUNT = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(userName);
        Account account = new Account();
        Account resAccount = accountModel.findAccountByUserName(account, userName);

        if (account == null){
            resp.getWriter().println("Invalid information!");
            return;
        }
        if (account.getStatus() == 2){
            if (LocalDateTime.now().compareTo(account.getLockTime()) > 0){
                account.setStatus(1);
                account.setFailureCount(0);
                accountModel.updateLock(account.getUserName(),account);
            }else{
                resp.getWriter().println("You account has been locked, please try again after " + account.getFullName());
                return;
            }
        }

        boolean checkPassword = PasswordHandler.checkPassword(password, account.getPassword(), account.getSalt());
        if (checkPassword){
            resp.getWriter().println("Login success");
        }else{
            account.setFailureCount(account.getFailureCount() + 1);
            if (account.getFailureCount() == MAX_COUNT){
                account.setStatus(2);
                account.setLockTime(LocalDateTime.now().plusMinutes(5));
                accountModel.updateLock(account.getUserName(),account);
            }
            resp.getWriter().println("Login fail!");

        }
    }
}
