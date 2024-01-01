package com.example.a24h_coffee_client.model;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class TableBill {
    private String id;
    @SerializedName("gioVao")
    private Date timeIn;
    @SerializedName("soThuTu")
    private int stt;
    @SerializedName("trangThaiOrder")
    private String statusOrder;

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
