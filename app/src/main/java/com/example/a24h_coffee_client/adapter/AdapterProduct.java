package com.example.a24h_coffee_client.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.databinding.ItemProductBinding;
import com.example.a24h_coffee_client.model.Product;
import com.example.a24h_coffee_client.utils.FormatUtils;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    private List<Product> mList;

    public AdapterProduct(List<Product> list) {
        this.mList = list;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Product> products){
        this.mList = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding mBinding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = mList.get(position);
        if (product == null){
            return;
        }

        holder.bind(product);

    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        ItemProductBinding binding;
        public ViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        @SuppressLint("SetTextI18n")
        public void bind(Product product){
            Glide.with(binding.getRoot()).load(product.getImage()).into(binding.ivProductGrid);
            binding.tvNameProduct.setText(product.getName());
            binding.tvPriceProduct.setText(FormatUtils.formatCurrency(product.getPrice()));

            if (product.getStatus().equals("Còn hàng")){
                binding.tvStatusProduct.setText("Còn hàng");
                binding.tvStatusProduct.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.GreenPrimary));
            }else {
                binding.tvStatusProduct.setText("Hết hàng");
                binding.tvStatusProduct.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.RedPrimary));
            }
        }
    }
}
