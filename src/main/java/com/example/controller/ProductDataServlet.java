package com.example.controller;

import com.example.Constants;
import com.example.dao.ProductDao;
import com.example.dao.ProductDaoImpl;
import com.example.model.PagedProduct;
import com.example.model.PostData;
import com.example.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet("/productdata")
public class ProductDataServlet extends HttpServlet {

    private ProductDao db;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        db = new ProductDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String jsonString = req.getParameter("command");
        PostData post = mapper.readValue(jsonString, PostData.class);

        if (post.getCommand().equals(Constants.CMD_GET_PRODUCT)) {
            PagedProduct product = db.getPagedProducts(post.getPaging());
            out.write(mapper.writeValueAsString(product));
        } else if (post.getCommand().equals(Constants.CMD_GET_PRODUCT_DETAIL)) {
            Product product = db.getProduct(post.getProductId());
            out.write(mapper.writeValueAsString(product));
        }
    }
}
