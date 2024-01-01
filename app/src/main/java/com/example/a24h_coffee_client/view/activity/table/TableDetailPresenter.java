package com.example.a24h_coffee_client.view.activity.table;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;
import com.example.a24h_coffee_client.view.activity.table.response.ResponseBillDetail;
import com.example.a24h_coffee_client.view.activity.table.response.ResponseTableBill;

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
                    }else {
                        Log.d("getTableBill", "error");
                    }
                }else {
                    Log.d("getTableBill", "error isSuccessful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseTableBill> call, @NonNull Throwable t) {
                Log.d("getTableBill", t.toString());
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
}
