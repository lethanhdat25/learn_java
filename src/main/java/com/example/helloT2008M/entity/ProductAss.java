package com.example.helloT2008M.entity;

import com.example.helloT2008M.annotation.Column;
import com.example.helloT2008M.annotation.Id;
import com.example.helloT2008M.annotation.Table;
import com.example.helloT2008M.ultil.Constants.Constants;

@Table(name = "tbProducts")
public class ProductAss {
    @Id(name = "id", type = Constants.INT, isAutoIncrement = true, isPrimaryKey = true)
    public int id;
    @Column(name = "name", type = Constants.VARCHAR150)
    public  String name;
    @Column(name = "price", type = Constants.DOUBLE)
    public double price;
    @Column(name = "amount", type = Constants.INT)
    public int amount;
    @Column(name = "detail", type = Constants.TEXT)
    public String detail;

    public ProductAss() {
    }

    public ProductAss(String name, double price, int amount, String detail) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.detail = detail;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
