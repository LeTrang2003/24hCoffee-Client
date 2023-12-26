package com.example.a24h_coffee_client.view.fragment.home.response;
import com.example.a24h_coffee_client.model.Product;

import java.util.List;

public class ResponseProduct {
    private String status;
    private List<Product> products;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
