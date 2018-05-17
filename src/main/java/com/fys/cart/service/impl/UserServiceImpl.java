package com.fys.cart.service.impl;

import com.fys.cart.dao.UserDao;
import com.fys.cart.model.Order;
import com.fys.cart.model.OrderItem;
import com.fys.cart.model.Product;
import com.fys.cart.model.User;
import com.fys.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User selectUser(String username, String password) {
        return userDao.selectUser(username, password);
    }

    @Override
    public Product selectProductById(Integer id) {
        return userDao.selectProductById(id);
    }

    @Override
    public List<Product> selectProducts() {
        return userDao.selectProducts();
    }

    @Override
    public int insertOrder(Order order) {
        return userDao.insertOrder(order);
    }

    @Override
    public int insertOrderItem(OrderItem orderItem) {
        return userDao.insertOrderItem(orderItem);
    }
}
