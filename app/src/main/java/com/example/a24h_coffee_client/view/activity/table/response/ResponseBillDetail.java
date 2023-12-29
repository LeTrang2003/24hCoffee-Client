package com.example.a24h_coffee_client.view.activity.table.response;

import com.example.a24h_coffee_client.model.BillDetail;
import com.example.a24h_coffee_client.model.TableBill;

import java.util.List;

public class ResponseBillDetail {
    private String status;
    private List<BillDetail> billDetails;

    public List<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(List<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
