package com.example.a24h_coffee_client.view.activity.table;

import com.example.a24h_coffee_client.model.BillDetail;
import com.example.a24h_coffee_client.model.TableBill;

import java.util.List;

public interface TableDetailContract {

    interface View {
        void onTableBill(TableBill tableBill);
        void onTableBillNo();
        void onListBillDetail(List<BillDetail> billDetails);
        void onInsertBill(String billId);
        void onMessagePaymentBill();
        void onInsertNotification();
    }

    interface Presenter {
        void getTableBill(int tableID);
        void getListBillDetail(String billID);
        void insertBill(String id, int tableId, String userId);
        void insertBillDetail(int quantity, double intoMoney, int productID, String billID);
        void paymentBill(String billId, int tableId, String timeOut, String datePayment);
        void insertNotification(String content, String userId);
    }
}
