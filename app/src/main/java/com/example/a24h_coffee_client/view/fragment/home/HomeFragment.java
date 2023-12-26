package com.example.a24h_coffee_client.view.fragment.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.adapter.AdapterBanner;
import com.example.a24h_coffee_client.adapter.AdapterCategory;
import com.example.a24h_coffee_client.adapter.AdapterProduct;
import com.example.a24h_coffee_client.databinding.FragmentHomeBinding;
import com.example.a24h_coffee_client.model.Banner;
import com.example.a24h_coffee_client.model.Category;
import com.example.a24h_coffee_client.model.Product;
import com.example.a24h_coffee_client.utils.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeContract.View {
    private FragmentHomeBinding mBinding;
    private HomeContract.Presenter mPresenter;
    private List<Product> mProductList;
    private AdapterProduct adapterProduct;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        mPresenter = new HomePresenter(this);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getListBanner();
        mPresenter.getListCategories();
        mPresenter.getListProduct();
    }

    @Override
    public void onListBanner(List<Banner> list) {
        AdapterBanner adapterBanner = new AdapterBanner(list);
        mBinding.vpSlideImage.setAdapter(adapterBanner);
        mPresenter.autoNextBanner(mBinding.vpSlideImage, list);
        mBinding.circleIndicator3.setViewPager(mBinding.vpSlideImage);
        mBinding.vpSlideImage.setPageTransformer(new DepthPageTransformer());
    }

    @Override
    public void onListCategory(List<Category> categories) {
        AdapterCategory adapterCategory = new AdapterCategory(categories, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mBinding.rcvProductCategory.setLayoutManager(layoutManager);
        mBinding.rcvProductCategory.setAdapter(adapterCategory);
    }

    @Override
    public void onListProduct(List<Product> products) {
         adapterProduct = new AdapterProduct(products);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mBinding.rcvProduct.setLayoutManager(layoutManager);
        mBinding.rcvProduct.setAdapter(adapterProduct);
        mProductList = products;
    }

    @Override
    public void onItemClickListener(int id) {
        List<Product> productList = new ArrayList<>();
        if (id == 0){
            adapterProduct.setList(mProductList);
        }else {
            for (Product product: mProductList) {
                if (product.getCategoryID() == id){
                    productList.add(product);
                }
            }

            adapterProduct.setList(productList);
        }

    }
}