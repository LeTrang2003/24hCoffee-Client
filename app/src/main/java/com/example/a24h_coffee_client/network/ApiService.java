package com.example.a24h_coffee_client.network;

import com.example.a24h_coffee_client.view.activity.account.ResponseUser;
import com.example.a24h_coffee_client.view.fragment.home.response.ResponseBanner;
import com.example.a24h_coffee_client.view.fragment.home.response.ResponseCategory;
import com.example.a24h_coffee_client.view.fragment.home.response.ResponseProduct;
import com.example.a24h_coffee_client.view.fragment.table.ResponseTable;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    // account
    @POST(ManagerUrl.LOGIN)
    @FormUrlEncoded
    Call<ResponseUser> login(@Field("userName") String userName,
                             @Field("password") String password);

    // user
    @POST(ManagerUrl.UPDATE_INF_USER)
    @FormUrlEncoded
    Call<ResponseUser> updateUser (@Field("userName") String userName,
                                   @Field("name") String name,
                                   @Field("image") String image);
    @POST(ManagerUrl.READ_USER)
    @FormUrlEncoded
    Call<ResponseUser> readUser (@Field("userName") String userName);

    // banner
    @GET(ManagerUrl.READ_BANNER)
    Call<ResponseBanner> readBanner();

    // product
    @GET(ManagerUrl.READ_PRODUCT)
    Call<ResponseProduct> readProduct();

    // table
    @GET(ManagerUrl.READ_TABLE)
    Call<ResponseTable> readTable();

    // category
    @GET(ManagerUrl.READ_CATEGORY)
    Call<ResponseCategory> readCategory();

    // bill
    @POST(ManagerUrl.INSERT_BILL)
    @FormUrlEncoded
    Call<ResponseUser> insertBill(@Field("userName") String userName,
                             @Field("password") String password);
}
