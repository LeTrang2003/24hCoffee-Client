package com.example.a24h_coffee_client.view.activity.table;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.adapter.AdapterBillDetail;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.databinding.ActivityTableDetailBinding;
import com.example.a24h_coffee_client.model.BillDetail;
import com.example.a24h_coffee_client.model.BillTemporary;
import com.example.a24h_coffee_client.model.Table;
import com.example.a24h_coffee_client.model.TableBill;
import com.example.a24h_coffee_client.utils.FormatUtils;
import com.example.a24h_coffee_client.view.activity.MainActivity;
import com.example.a24h_coffee_client.view.activity.product.ProductSelectActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TableDetailActivity extends AppCompatActivity implements TableDetailContract.View{
    private ActivityTableDetailBinding mBinding;
    private TableDetailContract.Presenter mPresenter;
    private TableBill mTableBill;
    private  List<BillDetail> mBillDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityTableDetailBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mPresenter = new TableDetailPresenter(this);
        mBillDetails = new ArrayList<>();
        setBackGroundBTNOrder();
        initPresenter();
        onClick();
    }

    private void setBackGroundBTNOrder() {
        if (getTable().getStatusOrder().equals("Trống")){
            mBinding.btnOrder.setEnabled(false);
            mBinding.btnOrder.setBackgroundColor(ContextCompat.getColor(this, R.color.Gray1Primary));
        }else {
            mBinding.btnOrder.setEnabled(true);
            mBinding.btnOrder.setBackgroundColor(ContextCompat.getColor(this, R.color.BrowPrimary));
        }
    }
    public void initPresenter() {
        mPresenter.getTableBill(getTable().getId());
    }
    private void onClick() {
        mBinding.btnBackTableDetail.setOnClickListener(view -> onBackPressed());
        mBinding.tvAddProductTableDetail.setOnClickListener(view -> startAddProductActivity());
        mBinding.btnOrder.setOnClickListener(view -> checkStatusTable());
    }

    private void startAddProductActivity() {
        Intent intent = new Intent(this, ProductSelectActivity.class);
        if (mTableBill != null){
            intent.putExtra("statusTable", mTableBill.getStatusOrder());
            intent.putExtra("billId", mTableBill.getId());
            intent.putExtra("tableIDTem", getTable().getId());
            intent.putExtra("billDetail", new Gson().toJson(mBillDetails));
        }else {
            intent.putExtra("statusTable", getTable().getStatusOrder());
            intent.putExtra("billId", "null");
            intent.putExtra("tableIDTem", getTable().getId());
            intent.putExtra("billDetail", new Gson().toJson(mBillDetails));
        }
        mLauncher.launch(intent);
    }
    private void checkStatusTable() {
        if (getTable().getStatusOrder().equals("Trống")){
            if (mBillDetails != null){
                mPresenter.insertBill( "HD"+FormatUtils.formatID(), getTable().getId(), getUsername());
            }else {
                Toast.makeText(this, "Không có đồ uống để đặt bàn", Toast.LENGTH_SHORT).show();
            }
        }else {
            mPresenter.paymentBill(mTableBill.getId(),
                    getTable().getId(),
                    FormatUtils.formatTimeOut(new Date()),
                    FormatUtils.formatDatePayment(new Date()),
                    FormatUtils.parseCurrency(mBinding.tvPriceOrder.getText().toString()));
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTableBill(TableBill tableBill) {
       mBinding.tvNameTableDetail.setText(AppConstants.TABLE + tableBill.getStt());
       mBinding.tvStatusTableDetail.setText(tableBill.getStatusOrder());
       mBinding.tvTimeInBillDetail.setText(FormatUtils.formatDate(tableBill.getTimeIn()));
       mBinding.tvOder.setText("Thanh toán");
       mPresenter.getListBillDetail(tableBill.getId());
       mTableBill = tableBill;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTableBillNo() {
        mBinding.tvNameTableDetail.setText(AppConstants.TABLE + getTable().getStt());
        mBinding.tvStatusTableDetail.setText(getTable().getStatusOrder());
        mBinding.layoutTimeTableDetail.setVisibility(View.GONE);
        mBinding.tvOder.setText("Đặt bàn");
    }

    public Table getTable() {
        Type type = new TypeToken<Table>() {}.getType();
        return new Gson().fromJson(getIntent().getStringExtra("table"), type);
    }
    public String getUsername() {
        SharedPreferences prefs = getSharedPreferences(AppConstants.PREFS_NAME, Context.MODE_PRIVATE);
        prefs.getString(AppConstants.KEY_USERNAME, "");
        return prefs.getString(AppConstants.KEY_USERNAME, "");
    }
    @Override
    public void onListBillDetail(List<BillDetail> billDetails) {
        Collections.reverse(billDetails);
        AdapterBillDetail adapterBillDetail = new AdapterBillDetail(billDetails);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mBinding.rcvTableDetail.setLayoutManager(layoutManager);
        mBinding.rcvTableDetail.setAdapter(adapterBillDetail);
        double sumPrice = 0;
        for (BillDetail billDetail : billDetails){
            sumPrice += billDetail.getPriceProduct() * billDetail.getQuantityProduct();
        }
        mBinding.tvPriceOrder.setText(FormatUtils.formatCurrency(sumPrice));
    }

    @Override
    public void onInsertBill(String billId) {
        for (int i = 0 ; i < mBillDetails.size() ; i ++){
           mPresenter.insertBillDetail(mBillDetails.get(i).getQuantityProduct(), mBillDetails.get(i).getPriceProduct(),
                   mBillDetails.get(i).getProductId(), billId);
        }
        onBackPressed();
    }

    @Override
    public void onMessagePaymentBill() {
        mPresenter.insertNotification("Thanh toán thành công hóa đơn " + mTableBill.getId(), getUsername());
    }

    @Override
    public void onInsertNotification() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private final ActivityResultLauncher<Intent> mLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK){
                            Intent intent = result.getData();
                            assert intent != null;
                            String status = intent.getStringExtra("status");

                            if (status.equals("Có khách")){
                                mPresenter.getListBillDetail(mTableBill.getId());
                            }else {

                                String strBilTem = intent.getStringExtra("billDetail");
                                Type listType = new TypeToken<List<BillDetail>>() {}.getType();
                                List<BillDetail> billDetails = new Gson().fromJson(strBilTem, listType);
                                mBillDetails.addAll(billDetails);
                                onListBillDetail(mBillDetails);
                                mBinding.btnOrder.setEnabled(true);
                                mBinding.btnOrder.setBackgroundColor(ContextCompat.getColor(this, R.color.BrowPrimary));
                            }
                        }
                    });
}