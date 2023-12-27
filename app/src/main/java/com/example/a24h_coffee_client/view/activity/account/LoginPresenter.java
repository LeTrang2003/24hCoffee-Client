package com.example.a24h_coffee_client.view.activity.account;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View mView;

    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void login(String userName, String password) {
        ApiClient.getClient().create(ApiService.class).login(userName, password).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(@NonNull Call<ResponseUser> call, @NonNull Response<ResponseUser> response) {
                if (response.isSuccessful()){
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                        mView.onMessage(AppConstants.SUCCESS, response.body().getUser());
                    }else {
                        mView.onMessage(AppConstants.ON_FAILURE_LOGIN, null);
                    }
                }else {
                    mView.onMessage(AppConstants.ON_FAILURE_LOGIN, null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseUser> call, @NonNull Throwable t) {

            }
        });
    }
}
