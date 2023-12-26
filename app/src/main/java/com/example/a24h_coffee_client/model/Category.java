package com.example.a24h_coffee_client.model;

import com.google.gson.annotations.SerializedName;

public class Category {
    private int id;
    @SerializedName("tenDanhMuc")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
