package com.fys.cart.dao;

import com.fys.cart.model.Order;
import com.fys.cart.model.OrderItem;
import com.fys.cart.model.Product;
import com.fys.cart.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {
    /**
     *  查询用户名和密码是否存在于数据库中, 用于用户登录
     * @param username
     * @param password
     * @return
     */
    @Select("select id,name,password from user where name = #{username} and password = #{password}")
    User selectUser(@Param("username")String username, @Param("password")String password);

    /**
     * 根据商品id查询商品
     */
    @Select("select id, name, price from product where id = #{id}")
    Product selectProductById(Integer id);

    /**
     * 显示商品列表
     */
    @Select("select id, name, price from product")
    List<Product> selectProducts();

    /**
     * 添加订单对象到数据库
     */
    @Insert("insert into t_order set uid = #{order.user.id}")
    @Options(useGeneratedKeys = true, keyProperty = "order.id", keyColumn = "id")
    int insertOrder(@Param("order") Order order);

    /**
     * 将OrderItem保存到数据库
     */
    @Insert("insert into orderItem  (pid, oid, number) values (#{orderItem.product.id}, #{orderItem.order.id}, #{orderItem.num})")
    int insertOrderItem(@Param("orderItem") OrderItem orderItem);
}
