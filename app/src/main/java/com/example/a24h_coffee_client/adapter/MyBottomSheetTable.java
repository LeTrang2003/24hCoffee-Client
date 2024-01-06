package com.example.a24h_coffee_client.adapter;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.databinding.LayoutBottomSheetTableBinding;
import com.example.a24h_coffee_client.model.Table;
import com.example.a24h_coffee_client.model.TableBill;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;
import com.example.a24h_coffee_client.utils.ItemClickUtils;
import com.example.a24h_coffee_client.view.activity.table.TableDetailContract;
import com.example.a24h_coffee_client.view.activity.table.response.ResponseBillDetail;
import com.example.a24h_coffee_client.view.fragment.table.ResponseTable;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBottomSheetTable extends BottomSheetDialogFragment implements ItemClickUtils {
    private LayoutBottomSheetTableBinding mBinding;
    private final TableDetailContract.View mView;
    private TableBill tableBill;
    private int iDTable;
    public MyBottomSheetTable(TableDetailContract.View mView, TableBill tableBill, int iDTable) {
        this.mView = mView;
        this.tableBill = tableBill;
        this.iDTable = iDTable;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        mBinding = LayoutBottomSheetTableBinding.inflate(LayoutInflater.from(getContext()));
        bottomSheetDialog.setContentView(mBinding.getRoot());
        getListTableEmpty();
        return bottomSheetDialog;
    }

    private void setAdapter(List<Table> tables) {
        AdapterTableBT adapterTableBT = new AdapterTableBT(tables, mView, this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        mBinding.rcvBtsTable.setLayoutManager(layoutManager);
        mBinding.rcvBtsTable.setAdapter(adapterTableBT);
    }

    public void getListTableEmpty() {
        ApiClient.getClient().create(ApiService.class).readTableEmpty().enqueue(new Callback<ResponseTable>() {
            @Override
            public void onResponse(@NonNull Call<ResponseTable> call, @NonNull Response<ResponseTable> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                        setAdapter(response.body().getTables());
                    }
                }else {
                    Log.d("deleteBillDetail", "error isSuccessful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseTable> call, @NonNull Throwable t) {
                Log.d("ER", t.toString());
            }
        });
    }

    @Override
    public void onItemClick(Table table) {
        mBinding.btnConfirmTable.setOnClickListener(view ->  callApiSwapTable(table));
    }

    private void callApiSwapTable(Table table) {
        ApiClient.getClient().create(ApiService.class).swapTableBill(tableBill.getId(), iDTable, table.getId()).enqueue(new Callback<ResponseBillDetail>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBillDetail> call, @NonNull Response<ResponseBillDetail> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                        dismiss();
                        mView.onConfirmTable();
                    }
                }else {
                    Log.d("deleteBillDetail", "error isSuccessful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBillDetail> call, @NonNull Throwable t) {

            }
        });
    }
}
