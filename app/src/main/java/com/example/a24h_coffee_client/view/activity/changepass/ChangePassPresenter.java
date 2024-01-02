package com.example.a24h_coffee_client.view.activity.changepass;

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
    private User user;

    public ChangePassPresenter(ChangePassContract.View view) {
        this.view = view;
    }



    @Override
    public void changePassword(String passwordAgain) {
        ApiClient.getClient().create(ApiService.class).changePassword(user.getUserName(), passwordAgain).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                if (response.isSuccessful()){
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body().getStatus()))){
                       view.onMessage(AppConstants.SUCCESS);
                    }else{
                        view.onMessage(AppConstants.ON_FAILURE_CHANGE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {

            }
        });
    }


    public void getUser(){
        String idUser = user.getUserName();
        if (idUser != null){
            ApiClient.getClient().create(ApiService.class).readUser(idUser).enqueue(new Callback<ResponseUser>() {
                @Override
                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                    if (response.isSuccessful()){
                        if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body().getStatus()))){
                            view.onMessage(AppConstants.SUCCESS);
                        }else{
                            view.onMessage(AppConstants.CALL_API_ERROR_MESSAGE);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseUser> call, Throwable t) {
                }
            });
        }
    }

    @Override
    public boolean doChange(String password, String passNew, String passAgain) {
        if (!ValidateUtils.validateChangePasswordIsEmpty(password, passNew, passAgain)) {
            view.onMessage(AppConstants.ENTER_INFO);
            return false;
        }
        if (!FormatUtils.isPasswordValid(password)) {
            view.onMessage(AppConstants.EMPTY_PASS);
            return false;
        }
        if (!FormatUtils.isPasswordValid(passNew)) {
            view.onMessage(AppConstants.EMPTY_PASSNEW);
            return false;
        }
        if (!ValidateUtils.validateChangePasswordEqual(passNew, passAgain)) {
            view.onMessage(AppConstants.PASS_NOT_DUPLICATES);
            return false;
        }
        return true;
    }

}
