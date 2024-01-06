package com.example.a24h_coffee_client.view.fragment.notification;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationPresenter implements NotificationContract.Presenter{

    private final NotificationContract.View mView;

    public NotificationPresenter(NotificationContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getListNotification(String userId) {
        ApiClient.getClient().create(ApiService.class).readNotification(userId).enqueue(new Callback<ResponseNotification>() {
            @Override
            public void onResponse(@NonNull Call<ResponseNotification> call, @NonNull Response<ResponseNotification> response) {
                if (response.isSuccessful()){
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                        mView.onListNotification(response.body().getNotifications());
                    }else {
                        mView.onListNotification(null);
                    }
                }else {
                    mView.onListNotification(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseNotification> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void updateNotification(String userId) {
        ApiClient.getClient().create(ApiService.class).updateNotification(userId).enqueue(new Callback<ResponseNotification>() {
            @Override
            public void onResponse(@NonNull Call<ResponseNotification> call, @NonNull Response<ResponseNotification> response) {
                if (response.isSuccessful()){
                    if (!AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                        Log.d("ER", "error");
                    }
                }else {
                    Log.d("ER", "error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseNotification> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void deleteNotification(int id) {
        ApiClient.getClient().create(ApiService.class).deleteNotification(id).enqueue(new Callback<ResponseNotification>() {
            @Override
            public void onResponse(@NonNull Call<ResponseNotification> call, @NonNull Response<ResponseNotification> response) {
                if (response.isSuccessful()){
                    if (!AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                        Log.d("ER", "error");
                    }
                }else {
                    Log.d("ER", "error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseNotification> call, @NonNull Throwable t) {

            }
        });
    }
}
