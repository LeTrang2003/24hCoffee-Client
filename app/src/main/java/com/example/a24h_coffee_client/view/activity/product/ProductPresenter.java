package com.example.a24h_coffee_client.view.activity.product;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;
import com.example.a24h_coffee_client.view.fragment.home.response.ResponseProduct;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPresenter implements ProductContract.Presenter{

    private final ProductContract.View mView;

    public ProductPresenter(ProductContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getListProduct() {
        ApiClient.getClient().create(ApiService.class).readProduct().enqueue(new Callback<ResponseProduct>() {
            @Override
            public void onResponse(@NonNull Call<ResponseProduct> call, @NonNull Response<ResponseProduct> response) {
                assert response.body() != null;
                if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                    mView.onListProduct(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseProduct> call, @NonNull Throwable t) {
                Log.d("ER", t.toString());
            }
        });
    }
}
