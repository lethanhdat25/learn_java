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

public class Delete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = new Product();
        ProductModel productModel = new ProductModel();
        try {
            productModel.delete(product, id);
            resp.sendRedirect("/products");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
