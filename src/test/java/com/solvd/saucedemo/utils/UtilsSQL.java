package com.solvd.saucedemo.utils;

import com.solvd.saucedemo.SaucedemoBaseTest;
import com.solvd.saucedemo.mappers.UserMapper;
import com.solvd.saucedemo.mappers.UserOrderMapper;
import com.solvd.saucedemo.models.User;
import com.solvd.saucedemo.models.UserOrder;
import com.solvd.saucedemo.mybatis_test.MyBatisTest;
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
        try {
            // Load MyBatis configuration
            InputStream inputStream = MyBatisTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            System.out.println("Created SESSION");
            // Open a session
            try (SqlSession session = sqlSessionFactory.openSession()) {
                // Get the UserOrderMapper
                UserMapper userMapper = session.getMapper(UserMapper.class);
                System.out.println("Trying getting USER");
                return userMapper.getUserById(id);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error retrieving user with ID: " + id, e);
        }
    }

    public static UserOrder getOrderByIdSQL(int orderId) {

        try (SqlSession session = openSession()) {
            UserOrderMapper userOrderMapper = session.getMapper(UserOrderMapper.class);
            return userOrderMapper.getUserOrderById(orderId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving order with ID: " + orderId, e);
        }

    }

    public List<UserOrder> getUserOrdersByUserIdSQL(int userId) {

        try (SqlSession session = openSession()) {
            UserOrderMapper userOrderMapper = session.getMapper(UserOrderMapper.class);
            return userOrderMapper.getUserOrdersByUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving order with ID: " + userId, e);
        }

    }
}
