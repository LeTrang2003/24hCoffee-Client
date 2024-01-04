package com.example.a24h_coffee_client.network;

import com.example.a24h_coffee_client.view.activity.account.ResponseUser;
import com.example.a24h_coffee_client.view.activity.table.response.ResponseBillDetail;
import com.example.a24h_coffee_client.view.activity.table.response.ResponseTableBill;
import com.example.a24h_coffee_client.view.fragment.home.response.ResponseBanner;
import com.example.a24h_coffee_client.view.fragment.home.response.ResponseCategory;
import com.example.a24h_coffee_client.view.fragment.home.response.ResponseProduct;
import com.example.a24h_coffee_client.view.fragment.notification.ResponseNotification;
import com.example.a24h_coffee_client.view.fragment.table.ResponseTable;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @POST(ManagerUrl.CHANGE_PASS)
    @FormUrlEncoded
    Call<ResponseUser> changePassword (@Field("userName") String userName,
                                      @Field("password") String password);

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
    Call<ResponseBillDetail> insertBill(@Field("id") String id,
                                        @Field("tableID") int tableID,
                                        @Field("userID") String userID);

    @POST(ManagerUrl.UPDATE_BILL)
    @FormUrlEncoded
    Call<ResponseBillDetail> updateBill(@Field("billId") String billId,
                                        @Field("tableId") int tableId,
                                        @Field("timeOut") String timeOut,
                                        @Field("datePayment") String datePayment);

    // bill detail
    @GET(ManagerUrl.READ_TABLE_BILL)
    Call<ResponseTableBill> readTableBill(@Path("tableID") int tableID);
    @GET(ManagerUrl.READ_DETAIL_BILL)
    Call<ResponseBillDetail> readDetailBill(@Path("billID") String billID);
    @POST(ManagerUrl.INSERT_BILL_DETAIL)
    @FormUrlEncoded
    Call<ResponseBillDetail> insertBillDetail(@Field("quantity") int quantity,
                                                @Field("intoMoney") double intoMoney,
                                                @Field("productID") int productID,
                                                @Field("billID") String billID);

    // notification
    @POST(ManagerUrl.READ_NOTIFICATION)
    @FormUrlEncoded
    Call<ResponseNotification> readNotification(@Field("userID") String userId);

    @POST(ManagerUrl.INSERT_NOTIFICATION)
    @FormUrlEncoded
    Call<ResponseNotification> insertNotification(@Field("content") String content,
                                                  @Field("userId") String userId);

    @POST(ManagerUrl.UPDATE_NOTIFICATION)
    @FormUrlEncoded
    Call<ResponseNotification> updateNotification(@Field("userID") String userId);

    @GET(ManagerUrl.DELETE_NOTIFICATION)
    Call<ResponseNotification> deleteNotification(@Path("id") int id);
}
