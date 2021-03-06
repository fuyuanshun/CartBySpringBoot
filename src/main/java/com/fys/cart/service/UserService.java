package com.fys.cart.service;

import com.fys.cart.model.Order;
import com.fys.cart.model.OrderItem;
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
     * 根据商品id查询商品
     */
    @Select("select id, name from product where id = #{id}")
    Product selectProductById(Integer id);

    /**
     * 显示商品列表
     */
    List<Product> selectProducts();

    /**
     * 添加订单对象到数据库
     */
    int insertOrder(Order order);

    /**
     * 将OrderItem保存到数据库
     */
    int insertOrderItem(OrderItem orderitem);
}
