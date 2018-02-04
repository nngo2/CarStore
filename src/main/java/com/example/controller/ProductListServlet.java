package com.example.controller;

import com.example.Constants;
import com.example.dao.ProductDb;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductListServlet", urlPatterns = {"/product", ""})
public class ProductListServlet extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        synchronized (this) {
            ProductDb db = (ProductDb)this.getServletContext().getAttribute(Constants.PRODUCT_DB);
            if (db == null) {
                db = new ProductDb();
                this.getServletContext().setAttribute(Constants.PRODUCT_DB, db);
            }
        }


    }

    @Override
    public void destroy() {
        this.getServletContext().removeAttribute(Constants.PRODUCT_DB);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDb db = (ProductDb)this.getServletContext().getAttribute(Constants.PRODUCT_DB);

        req.setAttribute("products", db.getAllProducts());

        req.getRequestDispatcher("/WEB-INF/jsp/product.jsp").forward(req,resp);
    }
}
