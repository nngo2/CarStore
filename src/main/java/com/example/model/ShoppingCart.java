package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items;
    private double totalPrice;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Product p) {
        items.add(new CartItem(p, 1));

        double total = 0.0;
        for(CartItem i : items) {
            total += i.getTotalPrice();
        }
        totalPrice = total;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
