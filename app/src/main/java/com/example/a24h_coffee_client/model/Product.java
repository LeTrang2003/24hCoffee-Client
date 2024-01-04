package com.example.a24h_coffee_client.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Product {
    private int id;
    @SerializedName("anhSanPham")
    private String image;
    @SerializedName("tenSanPham")
    private String name;
    @SerializedName("giaSanPham")
    private double price;
    @SerializedName("trangThaiSanPham")
    private String status;
    @SerializedName("danhMucID")
    private int categoryID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && categoryID == product.categoryID && Objects.equals(image, product.image) && Objects.equals(name, product.name) && Objects.equals(status, product.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, name, price, status, categoryID);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", categoryID=" + categoryID +
                '}';
    }
}
