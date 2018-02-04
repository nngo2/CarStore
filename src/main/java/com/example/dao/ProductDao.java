package com.example.dao;

import com.example.model.Product;

import java.util.Collection;

public interface ProductDao {
    void addProduct(Product p);
    Product getProduct(int id);
    Collection<Product> getAllProducts();
}
