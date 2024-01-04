package com.example.a24h_coffee_client.model;

import java.io.Serializable;
import java.util.Objects;

public class BillTemporary implements Serializable {
    private int quantity;
    private double intoMoney;
    private int productId;
    private String billId;

    public BillTemporary(int quantity, double intoMoney, int productId, String billId) {
        this.quantity = quantity;
        this.intoMoney = intoMoney;
        this.productId = productId;
        this.billId = billId;
    }

    public BillTemporary(int quantity, double intoMoney, int productId) {
        this.quantity = quantity;
        this.intoMoney = intoMoney;
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getIntoMoney() {
        return intoMoney;
    }

    public void setIntoMoney(double intoMoney) {
        this.intoMoney = intoMoney;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillTemporary that = (BillTemporary) o;
        return quantity == that.quantity && Double.compare(that.intoMoney, intoMoney) == 0 && productId == that.productId && Objects.equals(billId, that.billId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, intoMoney, productId, billId);
    }

    @Override
    public String toString() {
        return "BillTemporary{" +
                "quantity=" + quantity +
                ", intoMoney=" + intoMoney +
                ", productId=" + productId +
                ", billId='" + billId + '\'' +
                '}';
    }
}
