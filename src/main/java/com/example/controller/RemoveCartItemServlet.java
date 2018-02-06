package com.example.controller;

import com.example.Constants;
import com.example.dao.ProductDao;
import com.example.dao.ProductDaoImpl;
import com.example.model.PostData;
import com.example.model.Product;
import com.example.model.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RemoveCartItemServlet", urlPatterns = "/removeitem")
public class RemoveCartItemServlet extends HttpServlet {
    private ProductDao db;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        db = new ProductDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jsonString = req.getParameter("command");
        PostData post = mapper.readValue(jsonString, PostData.class);

        if (post.getCommand().equals(Constants.CMD_REMOVE_PRODUCT_CART)) {
            Product p = db.getProduct(post.getProductIdRemoveFromCart());

            HttpSession session =  req.getSession();

            ShoppingCart cart = (ShoppingCart)session.getAttribute(Constants.CARD_SESSION);
            if (cart == null) {
                session.setAttribute(Constants.CARD_SESSION, new ShoppingCart());
                cart = (ShoppingCart)session.getAttribute(Constants.CARD_SESSION);
            }

            cart.removeItem(p);
        }
    }
}
