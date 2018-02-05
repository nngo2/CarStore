package com.example.model;

public class PostData {
    private String command;
    private Paging paging;
    private int productIdAddToCart;
    private int productId;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public int getProductIdAddToCart() {
        return productIdAddToCart;
    }

    public void setProductIdAddToCart(int productIdAddToCart) {
        this.productIdAddToCart = productIdAddToCart;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
