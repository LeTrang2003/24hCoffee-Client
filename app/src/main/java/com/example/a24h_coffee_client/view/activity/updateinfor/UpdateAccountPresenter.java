package com.example.a24h_coffee_client.view.activity.updateinfor;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;
import com.example.a24h_coffee_client.view.activity.account.ResponseUser;

import java.util.Date;
import java.util.Objects;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAccountPresenter implements UpdateAccountContract.Presenter{
    private final UpdateAccountContract.View mView;

    public UpdateAccountPresenter(UpdateAccountContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void updateInFor(String userName, String name, String image, String phone, String dateOfBirth, String sex) {
        ApiClient.getClient().create(ApiService.class).updateUser(userName, name, image, phone, dateOfBirth, sex).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(@NonNull Call<ResponseUser> call, @NonNull Response<ResponseUser> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body().getStatus()))){
                        mView.onMessage("Cập nhật thông tin thành công");
                        mView.onUser(response.body().getUser());
                    }else{
                        mView.onMessage("Cập nhật thông tin thất bại");
                    }
                }else {
                    mView.onMessage("Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseUser> call, @NonNull Throwable t) {
                mView.onMessage(t.toString());
            }
        });
    }

    @Override
    public void updateInForFile(RequestBody userName, RequestBody name, MultipartBody.Part image, RequestBody phone, RequestBody dateOfBirth, RequestBody sex) {
        ApiClient.getClient().create(ApiService.class).updateUserFile(userName, name, image, phone, dateOfBirth, sex).enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(@NonNull Call<ResponseUser> call, @NonNull Response<ResponseUser> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body().getStatus()))){
                        mView.onMessage("Cập nhật thông tin thành công");
                        mView.onUser(response.body().getUser());
                    }else{
                        mView.onMessage("Cập nhật thông tin thất bại");
                    }
                }else {
                    mView.onMessage("Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseUser> call, @NonNull Throwable t) {
                mView.onMessage(t.toString());
            }
        });
    }
}
