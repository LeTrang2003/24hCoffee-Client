package com.example.a24h_coffee_client.view.activity.changepass;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.model.User;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;
import com.example.a24h_coffee_client.utils.FormatUtils;
import com.example.a24h_coffee_client.utils.ValidateUtils;
import com.example.a24h_coffee_client.view.activity.account.ResponseUser;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassPresenter implements ChangePassContract.Presenter {
    private final ChangePassContract.View view;

    public ChangePassPresenter(ChangePassContract.View view) {
        this.view = view;
    }

    @Override
    public void changePassword(String passwordAgain, String userId) {
        ApiClient.getClient().create(ApiService.class).changePassword(userId, passwordAgain).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(@NonNull Call<ResponseUser> call, @NonNull Response<ResponseUser> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body().getStatus()))){
                        view.onMessage("Đổi mật khẩu thành công");
                        view.onUser(response.body().getUser());
                    }else{
                        view.onMessage(AppConstants.ON_FAILURE_CHANGE);
                    }
                }else {
                    view.onMessage(AppConstants.ON_FAILURE_CHANGE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseUser> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public boolean doChange(String password, String passNew, String passAgain, Context context) {
        if (!ValidateUtils.validateChangePasswordIsEmpty(password, passNew, passAgain)) {
            view.onMessage(AppConstants.ENTER_INFO);
            return false;
        }

        if (!ValidateUtils.isPasswordValid(password, context)) {
            view.onMessage(AppConstants.EMPTY_PASS);
            return false;
        }

        if (!ValidateUtils.validateChangePasswordEqual(passNew, passAgain)) {
            view.onMessage(AppConstants.PASS_NOT_DUPLICATES);
            return false;
        }

        if (ValidateUtils.validatePasswordEqual(password, passNew)) {
            view.onMessage(AppConstants.EMPTY_PASSNEW);
            return false;
        }
        return true;
    }

}
