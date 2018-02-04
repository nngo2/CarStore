package com.example.controller;

import com.example.Constants;
import com.example.dao.ProductDao;
import com.example.dao.ProductDaoImpl;
import com.example.model.Product;
import com.example.model.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddCartItemServlet", urlPatterns = "/additem")
public class AddCartItemServlet extends HttpServlet {
    private ProductDao db;

    @Override
    public void init() throws ServletException {
        db = new ProductDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productid");
        int prodId = Integer.parseInt(productId);

        if (productId != null) {
            Product p = db.getProduct(prodId);

            HttpSession session =  req.getSession();

            ShoppingCart cart = (ShoppingCart)session.getAttribute(Constants.CARD_SESSION);
            if (cart == null) {
                session.setAttribute(Constants.CARD_SESSION, new ShoppingCart());
                cart = (ShoppingCart)session.getAttribute(Constants.CARD_SESSION);
            }

            cart.addItem(p);
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
