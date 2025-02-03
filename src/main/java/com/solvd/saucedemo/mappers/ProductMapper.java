package com.solvd.saucedemo.mappers;

import com.solvd.saucedemo.models.Product;

import java.util.List;

public interface ProductMapper {
    Product getProductById(int id);
    List<Product> getAllProducts();
}