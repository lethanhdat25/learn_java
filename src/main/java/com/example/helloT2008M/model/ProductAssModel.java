package com.example.helloT2008M.model;

import com.example.helloT2008M.Repository.MyRepository;
import com.example.helloT2008M.entity.Product;
import com.example.helloT2008M.entity.ProductAss;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductAssModel {
    public ProductAssModel() {

    }

    MyRepository<ProductAss> myRepository = new MyRepository<>();

    public List<ProductAss> getAll(ProductAss product) throws SQLException {
        ResultSet rs = myRepository.getAll(product);
        List<ProductAss> products = new ArrayList<>();

        while (rs.next()) {
                String name = rs.getString("name");
                Double price = Double.parseDouble(rs.getString("price"));
                int amount = Integer.parseInt(rs.getString("amount"));
                String detail = rs.getString("detail");
                ProductAss newProduct = new ProductAss( name, price,amount,detail);
                products.add(newProduct);
        }
        return products;

    }
}
