package com.example.a24h_coffee_client.view.activity.search;

import android.content.Context;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;
import com.example.a24h_coffee_client.view.fragment.home.response.ResponseProduct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchContract.Presenter{
    private final SearchContract.View view;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void getListProductAll() {
        ApiClient.getClient().create(ApiService.class).readProduct().enqueue(new Callback<ResponseProduct>() {
            @Override
            public void onResponse(Call<ResponseProduct> call, Response<ResponseProduct> response) {
                assert response.body() != null;
                if (AppConstants.SUCCESS.equals(response.body().getStatus()));
                view.onListProductAll(response.body().getProducts());
            }

            @Override
            public void onFailure(Call<ResponseProduct> call, Throwable t) {

            }
        });
    }

    @Override
    public void nextActivity(Context context) {

    }
}
