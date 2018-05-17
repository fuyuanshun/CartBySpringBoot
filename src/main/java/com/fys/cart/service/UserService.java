package com.fys.cart.service;

import com.fys.cart.model.Product;
import com.fys.cart.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserService{
    /**
     *  查询用户是否存在于数据库
     * @return
     */
    User selectUser(String username, String password);

    /**
     * 显示商品列表
     */
    List<Product> selectProducts();
}
