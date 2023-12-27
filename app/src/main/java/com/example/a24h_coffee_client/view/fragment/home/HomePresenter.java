package com.example.a24h_coffee_client.view.fragment.home;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.model.Banner;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;
import com.example.a24h_coffee_client.view.fragment.home.response.ResponseBanner;
import com.example.a24h_coffee_client.view.fragment.home.response.ResponseCategory;
import com.example.a24h_coffee_client.view.fragment.home.response.ResponseProduct;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.Presenter, Handler.Callback{
    private final HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void getListBanner() {
        ApiClient.getClient().create(ApiService.class).readBanner().enqueue(new Callback<ResponseBanner>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBanner> call, @NonNull Response<ResponseBanner> response) {
                assert response.body() != null;
                if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                    view.onListBanner(response.body().getBanners());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBanner> call, @NonNull Throwable t) {
                Log.d("ER", t.toString());
            }
        });
    }

    @Override
    public void getListCategories() {
        ApiClient.getClient().create(ApiService.class).readCategory().enqueue(new Callback<ResponseCategory>() {
            @Override
            public void onResponse(@NonNull Call<ResponseCategory> call, @NonNull Response<ResponseCategory> response) {
                assert response.body() != null;
                if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                    view.onListCategory(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseCategory> call, @NonNull Throwable t) {
                Log.d("ER", t.toString());
            }
        });
    }

    @Override
    public void getListProduct() {
        ApiClient.getClient().create(ApiService.class).readProduct().enqueue(new Callback<ResponseProduct>() {
            @Override
            public void onResponse(@NonNull Call<ResponseProduct> call, @NonNull Response<ResponseProduct> response) {
                assert response.body() != null;
                if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                    view.onListProduct(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseProduct> call, @NonNull Throwable t) {
                Log.d("ER", t.toString());
            }
        });
    }

    @Override
    public void autoNextBanner(ViewPager2 pager2, List<Banner> list) {
        Handler.Callback callback = new HomePresenter(view);
        Handler mHandler = new Handler(callback);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (pager2.getCurrentItem() == list.size() - 1) {
                    pager2.setCurrentItem(0);
                } else {
                    pager2.setCurrentItem(pager2.getCurrentItem() + 1);
                }
                mHandler.postDelayed(this, 2000); // Lặp lại sau 2 giây
            }
        };

        mHandler.postDelayed(runnable, 2000); // Chạy lần đầu sau 2 giây
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        return true;
    }
}
