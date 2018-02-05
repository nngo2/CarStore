package com.example.controller;

import com.example.dao.ProductDao;
import com.example.dao.ProductDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductListServlet", urlPatterns = {"/product", ""})
public class ProductListServlet extends HttpServlet {

    private ProductDao db;

    @Override
    public void init() throws ServletException {
        db = new ProductDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", db.getAllProducts());
        req.setAttribute("page", db.getPagingInfo());
        req.getRequestDispatcher("/WEB-INF/jsp/product.jsp").forward(req,resp);
    }
}