package com.example.helloT2008M.entity;

import com.example.helloT2008M.annotation.Column;
import com.example.helloT2008M.annotation.Id;
import com.example.helloT2008M.annotation.Table;
import com.example.helloT2008M.ultil.Constants.Constants;

import java.time.LocalDateTime;
import java.util.HashMap;

@Table(name = "Products")
public class Product {
    @Id(name = "id", type = Constants.INT, isPrimaryKey = true, isAutoIncrement = true)
    public int id;

    @Column(name = "name", type = Constants.VARCHAR150)
    public String name;

    @Column(name = "category", type = Constants.VARCHAR150)
    public String category;

    @Column(name = "description", type = Constants.VARCHAR150)
    public String description;

    @Column(name = "image", type = Constants.TEXT)
    public String image;

    @Column(name = "price", type = Constants.DOUBLE)
    public double price;

    @Column(name = "createAt", type = Constants.DATETIME)
    public LocalDateTime createAt;

    @Column(name = "updateAt", type = Constants.DATETIME)
    public LocalDateTime updateAt;

    @Column(name = "status", type = Constants.VARCHAR150)
    public String status;

    public Product() {
    }

    public Product(int id,String name, String category, String description, String image, double price, LocalDateTime createAt, LocalDateTime updateAt, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
        this.price = price;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
    }

    public Product(String name, String category, String description, String image, double price, LocalDateTime createAt, LocalDateTime updateAt, String status) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
        this.price = price;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name="error", type = "string", isCreate = false)
    private HashMap<String,String> error;

    private void checkValid(){
        this.error = new HashMap<>();
        if (this.name == null ){
            this.error.put("name","Name is required!");
        }else {
            if (this.name.length() <= 7) {
                this.error.put("name", "Name must be greater than 7!");
            }
        }

        if (this.category == null || this.category.length() == 0){
            this.error.put("category","category is required!");
        }
        if (this.description == null || this.description.length() == 0){
            this.error.put("description","description is required!");
        }
        if (this.description == null || this.description.length() == 0){
            this.error.put("description","description is required!");
        }
        if (this.price <= 0 ){
            this.error.put("price","Price must be greater than 0!");
        }
        if (this.status == null || this.status.length() == 0){
            this.error.put("status","status is required!");
        }
    }

    public HashMap<String,String> getErrors() {
        checkValid();
        return error;
    }
    public boolean isValid() {
        checkValid();
        return error == null || error.size() == 0;
    }
}
