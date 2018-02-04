package com.example.model;

public class Product {
    private String id;
    private String name;
    private String description;
    private String image;
    private double unitPrice;

    public Product() {
        super();
    }

    public Product(String id, String name, String description, String image, double unitPrice) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
