package com.example.helloT2008M.controller.UserAss;

import com.example.helloT2008M.entity.UserAss;
import com.example.helloT2008M.model.UserAssModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class Login extends HttpServlet {
    UserAssModel userAssModel = new UserAssModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Example/Login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        UserAss userAss = new UserAss();
        UserAss resUser = userAssModel.findUserByName(userAss, userName);

        if (resUser == null) {
            req.setAttribute("userName","user not found!");
            req.getRequestDispatcher("/Example/Login.jsp").forward(req,resp);
        }else {
            if (!Objects.equals(password, resUser.getPassword())) {
                req.setAttribute("password", "password is  not correct!");
                req.getRequestDispatcher("/Example/Login.jsp").forward(req, resp);
            }

            req.setAttribute("userName",resUser.getUsername());
            req.getRequestDispatcher("/Example/Product.jsp").forward(req, resp);
        }


    }
}
