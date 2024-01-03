package com.example.a24h_coffee_client.view.activity.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.adapter.AdapterProduct;
import com.example.a24h_coffee_client.adapter.AdapterSeclectProduct;
import com.example.a24h_coffee_client.databinding.ActivityProductBinding;
import com.example.a24h_coffee_client.model.Product;

import java.util.List;

public class ProductActivity extends AppCompatActivity implements ProductContract.View {
    private ActivityProductBinding mBinding;
    private ProductContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mPresenter = new ProductPresenter(this);
        initPresenter();
        onClick();
    }

    private void initPresenter() {
        mPresenter.getListProduct();
    }

    private void onClick() {

    }

    @Override
    public void onListProduct(List<Product> products) {
        AdapterSeclectProduct adapterSeclectProduct = new AdapterSeclectProduct(products);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.rcvSeclectProduct.setLayoutManager(layoutManager);
        mBinding.rcvSeclectProduct.setAdapter(adapterSeclectProduct);
    }

    @Override
    public void onItemClickListener(double price) {

    }
}