package com.example.dao;

import com.example.model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductDb {
    private Map<Integer, Product> products;

    public ProductDb() {
        products = new HashMap<>();
        init();
    }

    private void init() {
        String desc = "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto ";
        Product p = new Product(1,"Car 1", "Car 1 " + desc, "product1.jpg", 20000.00, "Coupe", "Mercedes", 2017);
        addProduct(p);
        p = new Product(2,"Car 2", "Car 2 " + desc, "product2.jpg", 25000.00, "Coupe", "Mercedes", 2017);
        addProduct(p);
        p = new Product(3,"Car 3", "Car 3 " + desc, "product3.jpg", 35000.00, "Coupe", "Mercedes", 2017);
        addProduct(p);
        p = new Product(4,"Car 4", "Car 4 " + desc, "product4.jpg", 55000.00, "Coupe", "Mercedes", 2017);
        addProduct(p);
    }

    public void addProduct(Product p) {
        products.put(p.getId(), p);
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }
}
