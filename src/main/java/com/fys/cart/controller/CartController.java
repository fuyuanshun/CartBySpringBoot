package com.fys.cart.controller;

import com.fys.cart.model.Product;
import com.fys.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/productList")
    public String productList(HttpServletRequest req, HttpServletRequest resp) {
        List<Product> productList = userService.selectProducts();
        req.setAttribute("productList", productList);
        return "productList";
    }
}
