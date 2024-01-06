package com.example.a24h_coffee_client.view.activity.product;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;
import com.example.a24h_coffee_client.view.activity.table.response.ResponseBillDetail;
import com.example.a24h_coffee_client.view.fragment.home.response.ResponseProduct;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductSelectPresenter implements ProductSelectContract.Presenter{

    private final ProductSelectContract.View mView;

    public ProductSelectPresenter(ProductSelectContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getListProduct() {
        ApiClient.getClient().create(ApiService.class).readHaveProduct().enqueue(new Callback<ResponseProduct>() {
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

    @Override
    public void insertBillDetail(int quantity, double intoMoney, int productID, String billID) {
        ApiClient.getClient().create(ApiService.class).insertBillDetail(quantity, intoMoney, productID, billID).enqueue(new Callback<ResponseBillDetail>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBillDetail> call, @NonNull Response<ResponseBillDetail> response) {
                assert response.body() != null;
                if (!AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                    Log.d("ER", "error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBillDetail> call, @NonNull Throwable t) {
                Log.d("ER", t.toString());
            }
        });
    }
}
