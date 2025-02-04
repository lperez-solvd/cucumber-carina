package com.solvd.saucedemo.mappers;

import com.solvd.saucedemo.models.UserOrder;

import java.util.List;

public interface UserOrderMapper {
    UserOrder getUserOrderById(int id);

    List<UserOrder> getAllUserOrders();

    UserOrder getUserOrderWithProducts(int orderId);

    List<UserOrder> getAllUserOrdersWithProducts();
}
