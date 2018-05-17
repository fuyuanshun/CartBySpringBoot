package com.fys.cart.service.impl;

import com.fys.cart.dao.UserDao;
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
    public List<Product> selectProducts() {
        return userDao.selectProducts();
    }
}
