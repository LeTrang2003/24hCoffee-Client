package com.example.a24h_coffee_client.view.fragment.setting;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;
import com.example.a24h_coffee_client.view.activity.account.ResponseUser;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingPresenter implements SettingContract.Presenter{
    private final SettingContract.View view;

    public SettingPresenter(SettingContract.View view) {
        this.view = view;
    }

    @Override
    public void getUser(String userName) {
        ApiClient.getClient().create(ApiService.class).readUser(userName).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.isSuccessful()){
                    if (AppConstants.SUCCESS.equals(response.body().getStatus())){
                        view.updateUI(response.body().getUser());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
            }
        });
    }

}
