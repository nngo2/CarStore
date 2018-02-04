package com.example.model;

public class CartItem {
    private int productId;
    private String productName;
    private double price;
    private int numOfItems;
    private double totalPrice;

    public CartItem(Product p, int num) {
        productId = p.getId();
        productName = p.getName();
        price = p.getUnitPrice();
        numOfItems = num;
        totalPrice = price * numOfItems;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
