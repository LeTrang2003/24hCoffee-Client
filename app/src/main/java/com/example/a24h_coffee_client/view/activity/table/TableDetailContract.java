package com.example.a24h_coffee_client.view.activity.table;

import com.example.a24h_coffee_client.model.BillDetail;
import com.example.a24h_coffee_client.model.TableBill;

import java.util.List;

public class TableDetailContract {

    interface View {
        void onTableBill(TableBill tableBill);
        void onListBillDetail(List<BillDetail> billDetails);
    }

    interface Presenter {
        void getTableBill(int tableID);
        void getListBillDetail(String billID);
    }
}
