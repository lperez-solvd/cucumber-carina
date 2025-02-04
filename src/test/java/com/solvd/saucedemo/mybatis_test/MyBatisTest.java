package com.solvd.saucedemo.mybatis_test;


import com.solvd.saucedemo.mappers.UserOrderMapper;
import com.solvd.saucedemo.models.UserOrder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {
    public static void main(String[] args) throws IOException {
        try {
            // Load MyBatis configuration
            InputStream inputStream = MyBatisTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // Open a session
            try (SqlSession session = sqlSessionFactory.openSession()) {
                // Get the UserOrderMapper
                UserOrderMapper userOrderMapper = session.getMapper(UserOrderMapper.class);

                // Get the first UserOrder for userId = 0
                List<UserOrder> orders = userOrderMapper.getAllUserOrdersWithProducts();
                UserOrder firstOrder = orders.isEmpty() ? null : orders.get(1);  // Getting the first order, if any

                // Print the first order to the console
                if (firstOrder != null) {
                    System.out.println("Order ID: " + firstOrder.getId());
                    System.out.println("User ID: " + firstOrder.getUserId());
                    System.out.println("Order Date: " + firstOrder.getOrderDate());
                    System.out.println("Products in this Order:");
                    for (com.solvd.saucedemo.models.Product product : firstOrder.getProducts()) {
                        System.out.println("  - Product ID: " + product.getId() + ", Name: " + product.getName() + ", Price: " + product.getPrice());
                    }
                } else {
                    System.out.println("No orders found for userId = 0.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
