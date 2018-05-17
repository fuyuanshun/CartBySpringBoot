package com.fys.cart.dao;

import com.fys.cart.model.Product;
import com.fys.cart.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
     * 添加商品到购物车
     */
    @Insert("insert into orderItem set pid = #{pid}, oid = #{oid}, number=#{num}")
    void insertOrderItem(@Param("pid")Integer pid, @Param("oid")Integer oid, @Param("num")String num);
}
