package com.example.a24h_coffee_client.view.fragment.table;

import com.example.a24h_coffee_client.model.Table;

import java.util.List;

public class ResponseTable {
    private String status;
    private List<Table> tables;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
