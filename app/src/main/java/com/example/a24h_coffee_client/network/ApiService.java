package com.example.a24h_coffee_client.network;

import com.example.a24h_coffee_client.view.activity.account.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    // account
    @POST(ManagerUrl.LOGIN)
    @FormUrlEncoded
    Call<ResponseUser> login(@Field("userName") String userName,
                             @Field("password") String password);
}
