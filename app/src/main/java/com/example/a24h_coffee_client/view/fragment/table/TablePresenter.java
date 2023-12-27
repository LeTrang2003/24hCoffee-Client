package com.example.a24h_coffee_client.view.fragment.table;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TablePresenter implements TableContract.Presenter{

    private final TableContract.View mView;

    public TablePresenter(TableContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getListTable() {
        ApiClient.getClient().create(ApiService.class).readTable().enqueue(new Callback<ResponseTable>() {
            @Override
            public void onResponse(@NonNull Call<ResponseTable> call, @NonNull Response<ResponseTable> response) {
                if (response.isSuccessful()){
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                        mView.onListTable(response.body().getTables());
                    }else {
                        Log.d("ER", "error");
                    }
                }else {
                    Log.d("ER", "error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseTable> call, @NonNull Throwable t) {
                Log.d("ER", t.toString());
            }
        });
    }
}
