package com.example.a24h_coffee_client.view.activity.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.adapter.AdapterSearch;
import com.example.a24h_coffee_client.databinding.ActivitySearchBinding;
import com.example.a24h_coffee_client.model.Product;
import com.example.a24h_coffee_client.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    private ActivitySearchBinding mBinding;
    private List<Product> products;
    private AdapterSearch adapterSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        SearchContract.Presenter mPresenter = new SearchPresenter(this);
        mPresenter.getListProductAll();
        setListener();
    }

    private void setListener(){
        mBinding.ivBack.setOnClickListener(view -> onBackPressed());
        mBinding.ivSearch.setOnClickListener(view -> performSearch());
    }
    private void performSearch(){
        String searchText = mBinding.etSearch.getText().toString().trim();
        if (ValidateUtils.isDataInputEmpty(searchText)){
            showUI(View.GONE, View.VISIBLE);
        }else {
            List<Product> filteredListProduct = new ArrayList<>();
            for (Product product : products){
                if (product.getName().toLowerCase().contains(searchText.toLowerCase())){
                    filteredListProduct.add(product);
                }
            }
            adapterSearch.setProductSearchList(filteredListProduct, this);

            if (filteredListProduct.size() == 0){
                showUI(View.GONE, View.VISIBLE);
            }else {
                showUI(View.VISIBLE, View.GONE);
            }
        }
    }

    private void showUI(int visibleRecyclerView, int visibleEmptyText){
        mBinding.recyclerView.setVisibility(visibleRecyclerView);
        mBinding.tvEmpty.setVisibility(visibleEmptyText);
    }

    @Override
    public void onListProductAll(List<Product> list) {
        adapterSearch = new AdapterSearch();
        RecyclerView recyclerView = mBinding.recyclerView;
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterSearch);
        products = list;
    }

    @Override
    public void nextDetailActivity(int id) {

    }
}