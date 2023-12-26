package com.example.a24h_coffee_client.model;

import com.google.gson.annotations.SerializedName;

public class Table {
    private int id;
    @SerializedName("soThuTu")
    private int stt;
    @SerializedName("trangThaiOrder")
    private String statusOrder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
}
