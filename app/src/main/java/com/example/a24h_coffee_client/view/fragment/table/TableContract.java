package com.example.a24h_coffee_client.view.fragment.table;

import com.example.a24h_coffee_client.model.Table;

import java.util.List;

public interface TableContract {

    interface View {
        void onListTable(List<Table> tables);
        void nextActivity(int tableId);
    }

    interface Presenter {
        void getListTable();
    }
}
