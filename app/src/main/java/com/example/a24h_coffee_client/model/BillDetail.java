package com.example.a24h_coffee_client.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

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
    @SerializedName("sanPhamID")
    private int productId;

    public BillDetail(int quantityProduct, String imageProduct, String nameProduct, double priceProduct, int productId) {
        this.quantityProduct = quantityProduct;
        this.imageProduct = imageProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillDetail that = (BillDetail) o;
        return id == that.id && quantityProduct == that.quantityProduct && Double.compare(that.priceProduct, priceProduct) == 0 && Objects.equals(imageProduct, that.imageProduct) && Objects.equals(nameProduct, that.nameProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantityProduct, imageProduct, nameProduct, priceProduct);
    }

    @Override
    public String toString() {
        return "BillDetail{" +
                "id=" + id +
                ", quantityProduct=" + quantityProduct +
                ", imageProduct='" + imageProduct + '\'' +
                ", nameProduct='" + nameProduct + '\'' +
                ", priceProduct=" + priceProduct +
                '}';
    }
}
