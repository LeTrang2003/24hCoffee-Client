package com.example.a24h_coffee_client.view.activity.table;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;
import com.example.a24h_coffee_client.view.activity.table.response.ResponseBillDetail;
import com.example.a24h_coffee_client.view.activity.table.response.ResponseTableBill;
import com.example.a24h_coffee_client.view.fragment.notification.ResponseNotification;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableDetailPresenter implements TableDetailContract.Presenter {
    private final TableDetailContract.View mView;

    public TableDetailPresenter(TableDetailContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getTableBill(int tableID) {
        ApiClient.getClient().create(ApiService.class).readTableBill(tableID).enqueue(new Callback<ResponseTableBill>() {
            @Override
            public void onResponse(@NonNull Call<ResponseTableBill> call, @NonNull Response<ResponseTableBill> response) {
                if (response.isSuccessful()){
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                        mView.onTableBill(response.body().getTableBill());
                        Log.d("onTableBill ", "isSuccessful");
                    }else {
                        mView.onTableBillNo();
                        Log.d("onTableBillNo ", "isSuccessful");
                    }
                }else {
                    Log.d("getTableBill 2 ", "error isSuccessful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseTableBill> call, @NonNull Throwable t) {
                Log.d("getTableBill 3", t.toString());
            }
        });
    }

    @Override
    public void getListBillDetail(String billID) {
        ApiClient.getClient().create(ApiService.class).readDetailBill(billID).enqueue(new Callback<ResponseBillDetail>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBillDetail> call, @NonNull Response<ResponseBillDetail> response) {
                if (response.isSuccessful()){
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                        mView.onListBillDetail(response.body().getBillDetails());
                    }else {
                        Log.d("getListBillDetail", "error");
                    }
                }else {
                    Log.d("getListBillDetail", "error isSuccessful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBillDetail> call, @NonNull Throwable t) {
                Log.d("getListBillDetail", t.toString());
            }
        });
    }

    @Override
    public void insertBill(String id, int tableId, String userId) {
        ApiClient.getClient().create(ApiService.class).insertBill(id, tableId, userId).enqueue(new Callback<ResponseBillDetail>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBillDetail> call, @NonNull Response<ResponseBillDetail> response) {
                if (response.isSuccessful()){
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                        mView.onInsertBill(id);
                    }else {
                        Log.d("insertBill", "error");
                    }
                }else {
                    Log.d("insertBill", "error isSuccessful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBillDetail> call, @NonNull Throwable t) {
                Log.d("insertBill", t.toString());
            }
        });
    }

    @Override
    public void insertBillDetail(int quantity, double intoMoney, int productID, String billID) {
        ApiClient.getClient().create(ApiService.class).insertBillDetail(quantity, intoMoney, productID, billID).enqueue(new Callback<ResponseBillDetail>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBillDetail> call, @NonNull Response<ResponseBillDetail> response) {
                assert response.body() != null;
                if (!AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                    Log.d("ER", "error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBillDetail> call, @NonNull Throwable t) {
                Log.d("ER", t.toString());
            }
        });
    }

    @Override
    public void paymentBill(String billId, int tableId, String timeOut, String datePayment, double intoMoney) {
        ApiClient.getClient().create(ApiService.class).updateBill(billId, tableId, timeOut, datePayment, intoMoney).enqueue(new Callback<ResponseBillDetail>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBillDetail> call, @NonNull Response<ResponseBillDetail> response) {
                assert response.body() != null;
                if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                    mView.onMessagePaymentBill();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBillDetail> call, @NonNull Throwable t) {
                Log.d("ER", t.toString());
            }
        });
    }

    @Override
    public void insertNotification(String content, String userId) {
        ApiClient.getClient().create(ApiService.class).insertNotification(content, userId).enqueue(new Callback<ResponseNotification>() {
            @Override
            public void onResponse(@NonNull Call<ResponseNotification> call, @NonNull Response<ResponseNotification> response) {
                assert response.body() != null;
                if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                    mView.onInsertNotification();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseNotification> call, @NonNull Throwable t) {

            }
        });
    }
}
