package com.solvd.saucedemo;

import com.solvd.saucedemo.utils.UtilsSQL;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.DataProvider;
import com.solvd.saucedemo.mappers.UserMapper;
import com.solvd.saucedemo.mappers.UserOrderMapper;
import com.solvd.saucedemo.models.User;
import com.solvd.saucedemo.models.UserOrder;

import java.util.List;

public class SaucedemoDataProvider {

    @DataProvider(name = "userOrders")
    public static Object[][] getUserOrders() {
        try (SqlSession session = UtilsSQL.openSession()) {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            UserOrderMapper orderMapper = session.getMapper(UserOrderMapper.class);

            List<User> users = userMapper.getAllUsers();
            Object[][] data = new Object[users.size()][2];

            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                List<UserOrder> orders = orderMapper.getUserOrdersByUserId(user.getId());
                data[i][0] = user;
                data[i][1] = orders;
            }
            return data;
        }
    }
}
