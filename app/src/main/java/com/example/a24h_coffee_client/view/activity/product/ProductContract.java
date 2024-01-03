package com.example.a24h_coffee_client.view.activity.product;

import com.example.a24h_coffee_client.model.Product;

import java.util.List;

public interface ProductContract {
    interface View {
        void onListProduct(List<Product> products);
        void onItemClickListener(double price);
    }

    interface Presenter {
        void getListProduct();
    }
}
