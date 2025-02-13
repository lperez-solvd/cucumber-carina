package com.solvd.saucedemo.utils;

import com.solvd.saucedemo.SaucedemoBaseTest;
import com.solvd.saucedemo.mappers.UserMapper;
import com.solvd.saucedemo.mappers.UserOrderMapper;
import com.solvd.saucedemo.models.Product;
import com.solvd.saucedemo.models.User;
import com.solvd.saucedemo.models.UserOrder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class UtilsSQL {
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        try {
            InputStream inputStream = SaucedemoBaseTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing MyBatis SQL Session Factory", e);
        }
    }

    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    public static User getUserByIdSQL(int id) {
        try (SqlSession session = openSession()) {
            {
                UserMapper userMapper = session.getMapper(UserMapper.class);
                return userMapper.getUserById(id);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving user with ID: " + id, e);
        }
    }

    public static UserOrder getOrderByIdSQL(int orderId) {

        try (SqlSession session = openSession()) {
            UserOrderMapper userOrderMapper = session.getMapper(UserOrderMapper.class);
            UserOrder userOrder = userOrderMapper.getUserOrderById(orderId);
            if (userOrder != null) {
                List<Product> products = userOrderMapper.getProductsByOrderId(orderId);
                userOrder.setProducts(products);
            }
            return userOrder;
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving order with ID: " + orderId, e);
        }

    }

}
