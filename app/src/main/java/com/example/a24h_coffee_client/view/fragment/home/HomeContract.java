package com.example.a24h_coffee_client.view.fragment.home;

import androidx.viewpager2.widget.ViewPager2;

import com.example.a24h_coffee_client.model.Banner;
import com.example.a24h_coffee_client.model.Category;
import com.example.a24h_coffee_client.model.Product;

import java.util.List;

public interface HomeContract {
    interface View {
        void onListBanner(List<Banner> list);
        void onListCategory(List<Category> categories);
        void onListProduct(List<Product> products);
        void onItemClickListener(int id);
    }

    interface Presenter {
        void getListBanner();
        void getListCategories();
        void getListProduct();
        void autoNextBanner(ViewPager2 pager2, List<Banner> list);
    }
}
