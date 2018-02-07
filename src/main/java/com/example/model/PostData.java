package com.example.model;

public class PostData {
    private String command;
    private Paging paging;
    private int productIdAddToCart;
    private int productIdRemoveFromCart;
    private int productId;
    private Product updatedProduct;
    private User user;

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

    public int getProductIdRemoveFromCart() { return productIdRemoveFromCart; }

    public void setProductIdAddToCart(int productIdAddToCart) {
        this.productIdAddToCart = productIdAddToCart;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getUpdatedProduct() {
        return updatedProduct;
    }

    public void setUpdatedProduct(Product updatedProduct) {
        this.updatedProduct = updatedProduct;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
