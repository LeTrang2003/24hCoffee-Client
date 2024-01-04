package com.example.a24h_coffee_client.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Notification {
    private int id;
    @SerializedName("ngayTao")
    private Date dateCreate;
    @SerializedName("trangThaiXem")
    private String statusSee;
    @SerializedName("noiDung")
    private String context;
    @SerializedName("nguoiDungID ")
    private String userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getStatusSee() {
        return statusSee;
    }

    public void setStatusSee(String statusSee) {
        this.statusSee = statusSee;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
