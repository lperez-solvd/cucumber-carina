package com.solvd.saucedemo.models;

import java.sql.Timestamp;
import java.util.List;

public class UserOrder {
    private int id;
    private int userId;
    private Timestamp orderDate;  // Use Timestamp for date and time values
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", products=" + products +
                '}';
    }
}

