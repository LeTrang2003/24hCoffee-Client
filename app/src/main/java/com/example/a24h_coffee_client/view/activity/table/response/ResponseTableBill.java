package com.example.a24h_coffee_client.view.activity.table.response;

import com.example.a24h_coffee_client.model.Table;
import com.example.a24h_coffee_client.model.TableBill;

import java.util.List;

public class ResponseTableBill {
    private String status;
    private TableBill tableBill;

    public TableBill getTableBill() {
        return tableBill;
    }

    public void setTableBill(TableBill tableBill) {
        this.tableBill = tableBill;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
