package com.example.a24h_coffee_client.view.activity.table;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.adapter.AdapterBillDetail;
import com.example.a24h_coffee_client.adapter.AdapterCategory;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.databinding.ActivityTableDetailBinding;
import com.example.a24h_coffee_client.model.BillDetail;
import com.example.a24h_coffee_client.model.TableBill;
import com.example.a24h_coffee_client.utils.FormatUtils;
import com.example.a24h_coffee_client.view.activity.product.ProductActivity;

import java.util.List;

public class TableDetailActivity extends AppCompatActivity implements TableDetailContract.View{
    private ActivityTableDetailBinding mBinding;
    private TableDetailContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityTableDetailBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mPresenter = new TableDetailPresenter(this);
        initPresenter();
        onClick();
    }

    private void onClick() {
        mBinding.btnBackTableDetail.setOnClickListener(view -> onBackPressed());
        mBinding.tvAddProductTableDetail.setOnClickListener(view -> startActivity(new Intent(this, ProductActivity.class)));
    }

    public void initPresenter() {
        mPresenter.getTableBill(getIntent().getIntExtra("tableID", 0));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTableBill(TableBill tableBill) {
       if (tableBill.getId() != null){
           mBinding.tvNameTableDetail.setText(AppConstants.TABLE + tableBill.getStt());
           mBinding.tvStatusTableDetail.setText(tableBill.getStatusOrder());
           mBinding.tvTimeInBillDetail.setText(FormatUtils.formatDate(tableBill.getTimeIn()));
           mBinding.tvOder.setText("Thanh toán");
           mPresenter.getListBillDetail(tableBill.getId());
       }else {
           mBinding.tvNameTableDetail.setText(AppConstants.TABLE + tableBill.getStt());
           mBinding.tvStatusTableDetail.setText("Còn trống");
           mBinding.layoutTimeTableDetail.setVisibility(View.GONE);
           mBinding.tvOder.setText("Đặt bàn");
       }
    }

    @Override
    public void onListBillDetail(List<BillDetail> billDetails) {
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
}