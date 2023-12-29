package com.example.a24h_coffee_client.model;

import com.google.gson.annotations.SerializedName;

public class BillDetail {
    private int id;
    @SerializedName("soLuong")
    private int quantityProduct;
    @SerializedName("anhSanPham")
    private String imageProduct;
    @SerializedName("tenSanPham")
    private String nameProduct;
    @SerializedName("giaSanPham")
    private double priceProduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }
}
