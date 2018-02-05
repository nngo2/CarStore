package com.example.controller;

import com.example.Constants;
import com.example.model.AppStatus;
import com.example.model.ShoppingCart;
import com.example.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/appstatus")
public class AppStatusServlet extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        AppStatus status = new AppStatus();

        HttpSession session =  req.getSession();

        ShoppingCart cart = (ShoppingCart)session.getAttribute(Constants.CARD_SESSION);
        if (cart == null) {
            status.setCartEmpty(true);
        } else {
            status.setCartEmpty(false);
        }

        User u = (User)session.getAttribute(Constants.USER_SESSION);
        if (u == null) {
            status.setLoggedIn(false);
        } else {
            status.setLoggedIn(true);
        }

        out.write(mapper.writeValueAsString(status));
    }
}
