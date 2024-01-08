package com.example.a24h_coffee_client.view.activity.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.a24h_coffee_client.adapter.AdapterSeclectProduct;
import com.example.a24h_coffee_client.databinding.ActivityProductBinding;
import com.example.a24h_coffee_client.model.BillDetail;
import com.example.a24h_coffee_client.model.BillTemporary;
import com.example.a24h_coffee_client.model.Product;
import com.example.a24h_coffee_client.view.activity.table.TableDetailActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductSelectActivity extends AppCompatActivity implements ProductSelectContract.View {
    private ActivityProductBinding mBinding;
    private ProductSelectContract.Presenter mPresenter;
    private List<BillTemporary> billTemporaries;
    private List<BillDetail> mBillDetails;
    private List<Product> mProducts;
    private AdapterSeclectProduct adapterSeclectProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mPresenter = new ProductSelectPresenter(this);
        billTemporaries = new ArrayList<>();
        mBillDetails = new ArrayList<>();

        initPresenter();
        onClick();

    }

    private void onCheckList() {
        String strBilTem = getIntent().getStringExtra("billDetail");
        Type listType = new TypeToken<List<BillDetail>>() {}.getType();
        List<BillDetail> billDetails = new Gson().fromJson(strBilTem, listType);
        if (billDetails != null){
           for (int i = 0 ; i < billDetails.size() ; i ++){
               for (int j = 0 ; j < mProducts.size() ; j ++){
                   if (billDetails.get(i).getNameProduct().equals(mProducts.get(j).getName())){
                       mProducts.remove(mProducts.get(j));
                   }
               }
           }
           adapterSeclectProduct.setList(mProducts);
        }
    }

    private void initPresenter() {
        mPresenter.getListProduct();
    }

    private void onClick() {
        mBinding.btnConfirm.setOnClickListener(view -> checkStatusTable());
        mBinding.btnBackTableDetail.setOnClickListener(view -> onBackPressed());
        mBinding.etSearchProductActivity.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (mProducts != null) {
                    adapterSeclectProduct.getFilter().filter(s);
                }else {
                    Toast.makeText(ProductSelectActivity.this, "Không có nhân viên để tìm kiếm", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (mProducts != null) {
                    adapterSeclectProduct.getFilter().filter(s);
                }else {
                    Toast.makeText(ProductSelectActivity.this, "Không có nhân viên để tìm kiếm", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    private void checkStatusTable() {
        Intent intent = new Intent(this, TableDetailActivity.class);
        String status = getIntent().getStringExtra("statusTable");
        int tableIdTem = getIntent().getIntExtra("tableIDTem",0);

        if (status.equals("Có khách")){
            for (int i = 0 ; i < billTemporaries.size(); i ++){
                mPresenter.insertBillDetail(billTemporaries.get(i).getQuantity(), billTemporaries.get(i).getIntoMoney(),
                        billTemporaries.get(i).getProductId(), getIntent().getStringExtra("billId"));
            }
        }else {
            intent.putExtra("billDetail", new Gson().toJson(mBillDetails));
        }

        intent.putExtra("status", status);
        intent.putExtra("tableID", tableIdTem);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onListProduct(List<Product> products) {
        adapterSeclectProduct = new AdapterSeclectProduct(products, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.rcvSeclectProduct.setLayoutManager(layoutManager);
        mBinding.rcvSeclectProduct.setAdapter(adapterSeclectProduct);
        mProducts = products;
        onCheckList();
    }

    @Override
    public void onItemClickListener(BillTemporary billTemporary, BillDetail billDetail, boolean checkSelect) {
        if (checkSelect){
            billTemporaries.add(billTemporary);
            mBillDetails.add(billDetail);
        }else {
            billTemporaries.remove(billTemporary);
            mBillDetails.remove(billDetail);
        }
    }

    @Override
    public void onUpdateItemClickListener(int productId,String product, int quantity) {
        for (int i = 0 ; i < billTemporaries.size() ; i ++){
            if (productId == billTemporaries.get(i).getProductId()){
                billTemporaries.get(i).setQuantity(quantity);
            }
        }
        for (int i = 0 ; i < mBillDetails.size() ; i ++){
            if (product.equals(mBillDetails.get(i).getNameProduct())){
                mBillDetails.get(i).setQuantityProduct(quantity);
            }
        }
    }




}