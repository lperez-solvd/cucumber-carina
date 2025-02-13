package com.solvd.saucedemo.mappers;

import com.solvd.saucedemo.models.Product;
import com.solvd.saucedemo.models.UserOrder;

import java.util.List;

public interface UserOrderMapper {

    UserOrder getUserOrderById(int id);

    List<Product> getProductsByOrderId(int orderId);
}
