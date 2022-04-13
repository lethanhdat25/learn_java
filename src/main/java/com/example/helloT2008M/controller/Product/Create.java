package com.example.helloT2008M.controller.Product;

import com.example.helloT2008M.entity.Person;
import com.example.helloT2008M.entity.Product;
import com.example.helloT2008M.model.PersonModel;
import com.example.helloT2008M.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Create extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/Product/CreateProduct.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String defaultStatus = "Đang Bán";
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String description = req.getParameter("description");
        String image = req.getParameter("image");
        double price = Double.parseDouble(req.getParameter("price"));
        LocalDateTime createAt = LocalDateTime.now();
        LocalDateTime updateAt = LocalDateTime.now();
        String status = defaultStatus;

        Product product = new Product(name,category,description,image,price,createAt,updateAt,status);
        if (!product.isValid()){
            System.out.println(product.getErrors());
            req.setAttribute("errors",product.getErrors());
            req.getRequestDispatcher("/Product/CreateProduct.jsp").forward(req, resp);
            return;
        }
        ProductModel productModel = new ProductModel();
        try {
            productModel.save(product);
                resp.sendRedirect("/products");
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
