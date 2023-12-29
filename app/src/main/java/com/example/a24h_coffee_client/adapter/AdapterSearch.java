package com.example.a24h_coffee_client.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.databinding.ItemProductSearchBinding;
import com.example.a24h_coffee_client.model.Product;
import com.example.a24h_coffee_client.utils.FormatUtils;
import com.example.a24h_coffee_client.view.activity.search.SearchContract;

import java.util.List;

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.SearchViewHolder> {
    private List<Product> productList;
    private SearchContract.View mView;

    @SuppressLint("NotifyDataSetChanged")
    public void setProductSearchList(List<Product> productList, SearchContract.View mView) {
        this.productList = productList;
        this.mView = mView;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemProductSearchBinding itemProductSearchBinding = ItemProductSearchBinding.inflate(layoutInflater, parent, false);
        return new SearchViewHolder(itemProductSearchBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder{
        ItemProductSearchBinding searchBinding;

        public SearchViewHolder(@NonNull ItemProductSearchBinding searchBinding) {
            super(searchBinding.getRoot());
            this.searchBinding = searchBinding;
        }

        public void bind(Product product){
            Glide.with(itemView.getContext())
                    .load(product.getImage())
                    .into(searchBinding.ivProductSearch);
            searchBinding.tvNameProductSearch.setText(product.getName());
            searchBinding.tvPriceSearch.setText(FormatUtils.formatCurrency(product.getPrice()));
            if (product.getStatus().equals("Còn hàng")){
                searchBinding.tvStatusSearch.setText("Còn hàng");
                searchBinding.tvStatusSearch.setTextColor(ContextCompat.getColor(searchBinding.getRoot().getContext(), R.color.GreenPrimary));
            }else {
                searchBinding.tvStatusSearch.setText("Hết hàng");
                searchBinding.tvStatusSearch.setTextColor(ContextCompat.getColor(searchBinding.getRoot().getContext(), R.color.RedPrimary));
            }
        }
    }
}
