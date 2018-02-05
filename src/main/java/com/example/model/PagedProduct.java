package com.example.model;

import java.util.Collection;

public class PagedProduct {
    private Paging paging;
    private Collection<Product> products;

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }
}
