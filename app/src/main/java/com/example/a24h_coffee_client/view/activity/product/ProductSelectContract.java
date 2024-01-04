package com.example.a24h_coffee_client.view.activity.product;

import com.example.a24h_coffee_client.model.BillDetail;
import com.example.a24h_coffee_client.model.BillTemporary;
import com.example.a24h_coffee_client.model.Product;

import java.util.List;

public interface ProductSelectContract {
    interface View {
        void onListProduct(List<Product> products);
        void onItemClickListener(BillTemporary billTemporary, BillDetail billDetail, boolean checkSelect);
        void onUpdateItemClickListener(int productId, String nameProduct, int quantity);
    }

    interface Presenter {
        void getListProduct();
        void insertBillDetail(int quantity, double intoMoney, int productID, String billID);

    }
}
