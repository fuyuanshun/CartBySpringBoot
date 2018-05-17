package com.fys.cart.controller;

import com.fys.cart.model.Order;
import com.fys.cart.model.OrderItem;
import com.fys.cart.model.Product;
import com.fys.cart.model.User;
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
     * 生成订单
     */
    @RequestMapping("/createOrderItem")
    public void createOrderItem(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        Order order = new Order();
        order.setUser(user);

        int ret = userService.insertOrder(order);
        if (ret == 1) {
            List<OrderItem> orderItems = (List<OrderItem>) req.getSession().getAttribute("orderItems");

            for (OrderItem oi: orderItems) {
                oi.setOrder(order);
                System.out.println(oi.getProduct().getId());
                System.out.println(oi.getProduct().getName());
                System.out.println(oi.getNum());

                int ret2 = userService.insertOrderItem(oi);
                System.out.println(ret2);
            }
            try {
                /**
                 * 订单创建完成后，清空session
                 */
                orderItems.clear();
                resp.setContentType("text/html; charset=UTF-8");
                resp.getWriter().println("订单创建成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
