package com.example.helloT2008M.controller.Product;
import com.example.helloT2008M.entity.Person;
import com.example.helloT2008M.entity.Product;
import com.example.helloT2008M.model.PersonModel;
import com.example.helloT2008M.model.ProductModel;
import com.example.helloT2008M.ultil.Constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@MultipartConfig
public class Update extends HttpServlet {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ProductModel productModel = new ProductModel();
        Product product =new Product();
        Product rsProduct = productModel.findById(product,id);
        if (rsProduct == null){
            req.setAttribute("error","This product is not exist!");
            List<Product> products= null;
            try {
                products = productModel.getAll(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("products",products);
            req.getRequestDispatcher("/Product/ListProduct.jsp").forward(req,resp);
        }
        if (!Objects.equals(rsProduct.getStatus(), Constants.STATUS_SELLING)){
            req.setAttribute("error","This product is not selling!");
            List<Product> products= null;
            try {
                products = productModel.getAll(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("products",products);
            req.getRequestDispatcher("/Product/ListProduct.jsp").forward(req,resp);
        }
        req.setAttribute("updateProduct",rsProduct);
        req.getRequestDispatcher("/Product/CreateProduct.jsp").forward(req,resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String description = req.getParameter("description");
        String image = req.getParameter("image");
        double price = Double.parseDouble(req.getParameter("price"));
        LocalDateTime createAt = LocalDateTime.parse(req.getParameter("createAt"));
        LocalDateTime updateAt = LocalDateTime.now();
        String status = req.getParameter("status");

        Product product = new Product(name,category,description,image,price,createAt,updateAt,status);
        ProductModel productModel = new ProductModel();

        try {
            productModel.update(product,id);
            resp.sendRedirect("/products");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
