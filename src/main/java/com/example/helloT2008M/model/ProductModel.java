package com.example.helloT2008M.model;

import com.example.helloT2008M.Repository.MyRepository;
import com.example.helloT2008M.entity.Account;
import com.example.helloT2008M.entity.Product;
import com.example.helloT2008M.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductModel {

    public ProductModel() {

    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String SELLING = "Đang Bán";
    MyRepository<Product> myRepository = new MyRepository<>();

    public List<Product> getAll(Product product) throws SQLException {
        ResultSet rs = myRepository.getAll(product);
        List<Product> products = new ArrayList<>();

        while (rs.next()) {
            if (Objects.equals(rs.getString("status"), SELLING)) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                String description = rs.getString("description");
                String image = rs.getString("image");
                double price = Double.parseDouble(rs.getString("price"));
                LocalDateTime createAt = LocalDateTime.parse(rs.getString("createAt"), formatter);
                LocalDateTime updateAt = LocalDateTime.parse(rs.getString("updateAt"), formatter);
                String status = rs.getString("status");
                Product newProduct = new Product(id, name, category, description, image, price, createAt, updateAt, status);
                products.add(newProduct);
            }
        }
        return products;

    }

    public void save(Product product) throws InstantiationException, IllegalAccessException {
        myRepository.save(product);
    }

    public void update(Product product, int id) throws IllegalAccessException {

        myRepository.update(product, id);
    }

    public void delete(Product product, int id) {
        myRepository.delete(product, id);
    }

    public Product findById(Product product, int id){
        Product resProduct = null;
        try{
            ResultSet rs = myRepository.findById(product, id);
            while (rs.next()){
                String name = rs.getString("name");
                String category = rs.getString("category");
                String description = rs.getString("description");
                String image = rs.getString("image");
                double price = Double.parseDouble(rs.getString("price"));
                LocalDateTime createAt = LocalDateTime.parse(rs.getString("createAt"), formatter);
                LocalDateTime updateAt = LocalDateTime.parse(rs.getString("updateAt"), formatter);
                String status = rs.getString("status");
                resProduct = new Product(id, name, category, description, image, price, createAt, updateAt, status);
            }
        }catch (Exception e){
            return null;
        }
        return resProduct;
    }

}
