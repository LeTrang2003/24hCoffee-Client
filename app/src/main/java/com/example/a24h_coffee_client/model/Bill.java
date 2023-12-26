package com.example.a24h_coffee_client.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Bill {
    private int id;
    @SerializedName("gioVao")
    private Date timeOn;
    @SerializedName("gioRa")
    private Date timeOut;
    @SerializedName("ngayThanhToan")
    private Date datePayment;
    @SerializedName("trangThaiThanhToan")
    private String statusPayment;
    @SerializedName("banID")
    private int tableID;
    @SerializedName("nguoiDungID")
    private String userID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimeOn() {
        return timeOn;
    }

    public void setTimeOn(Date timeOn) {
        this.timeOn = timeOn;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    public String getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(String statusPayment) {
        this.statusPayment = statusPayment;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
