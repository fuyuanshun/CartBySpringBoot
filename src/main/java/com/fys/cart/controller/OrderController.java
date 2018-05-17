package com.fys.cart.controller;

import com.fys.cart.model.OrderItem;
import com.fys.cart.model.Product;
import com.fys.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private UserService userService;

    /**
     * 查看购物车
     */
    @RequestMapping("/orderItemList")
    public String orderItemList(HttpServletRequest req, HttpServletResponse resp) {

        return "OrderItemList";
    }
    /**
     *  添加商品到购物车
     * @param req
     * @param resp
     */
    @RequestMapping("/addOrderItem")
    public void addOrderItem(HttpServletRequest req, HttpServletResponse resp) {
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int pid = Integer.parseInt(req.getParameter("pid"));
        int num = Integer.parseInt(req.getParameter("num"));
        Product product = userService.selectProductById(pid);


        OrderItem orderItem = new OrderItem();
        orderItem.setNum(num);
        orderItem.setProduct(product);
        List<OrderItem> orderItems = (List<OrderItem>) req.getSession().getAttribute("orderItems");

        if(orderItems == null) {
            orderItems = new ArrayList<>();
            req.getSession().setAttribute("orderItems", orderItems);
        }

        boolean found = false;
        for (OrderItem oi: orderItems) {
            if(oi.getProduct().getId() == orderItem.getProduct().getId()) {
                oi.setNum(oi.getNum()+orderItem.getNum());
                found = true;
                break;
            }
        }

        if(!found)
            orderItems.add(orderItem);
        out.print("ok!");
    }

    /**
     * 删除购物车的物品
     */
    @RequestMapping("/delOrderItem")
    public String delOrderItem(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println(id);
        List<OrderItem> orderItems = (List<OrderItem>) req.getSession().getAttribute("orderItems");
        for (OrderItem orderItem : orderItems) {
            if(orderItem.getProduct().getId() == id) {
                orderItems.remove(orderItem);
                break;
            }
        }

        return "redirect:/orderItemList";
    }


    /**
     * 下订单
     */
    @RequestMapping("/createOrderItem")
    public String createOrderItem(HttpServletRequest req, HttpServletResponse resp) {

        return "";
    }
}
