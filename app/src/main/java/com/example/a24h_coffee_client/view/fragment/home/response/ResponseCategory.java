package com.example.a24h_coffee_client.view.fragment.home.response;

import com.example.a24h_coffee_client.model.Category;

import java.util.List;

public class ResponseCategory {
    private String status;
    private List<Category> categories;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
