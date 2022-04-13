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
import java.sql.SQLException;
import java.util.List;

public class GetAll extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = new Product();
        ProductModel productModel = new ProductModel();
        try {
            List<Product> products=productModel.getAll(product);
            req.setAttribute("products",products);
            req.getRequestDispatcher("/Product/ListProduct.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
