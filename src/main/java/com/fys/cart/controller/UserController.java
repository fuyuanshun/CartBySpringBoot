package com.fys.cart.controller;

import com.fys.cart.model.User;
import com.fys.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登陆处理
     * @param req
     * @param resp
     */
    @RequestMapping("/login")
    public void productList(HttpServletRequest req, HttpServletResponse resp) {
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.selectUser(username, password);

        if(null == user) {
            out.print("error");
        } else {
            req.getSession().setAttribute("user", user);
            out.print("success");
        }
    }

    /**
     * 退出， 将用户从session中移除
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("user");
        return "redirect:/";
    }
}
