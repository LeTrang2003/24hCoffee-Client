package com.example.a24h_coffee_client.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.databinding.ItemBannerBinding;
import com.example.a24h_coffee_client.databinding.ItemTableBinding;
import com.example.a24h_coffee_client.model.Banner;
import com.example.a24h_coffee_client.model.Table;

import java.util.List;

public class AdapterTable extends RecyclerView.Adapter<AdapterTable.viewHolder> {
    private final List<Table> mList;

    public AdapterTable(List<Table> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTableBinding mBinding = ItemTableBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new viewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Table table = mList.get(position);
        holder.bind(table);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private final ItemTableBinding binding;

        public viewHolder(ItemTableBinding  binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(Table table) {
         if (table.getStatusOrder().equals(AppConstants.TABLE_HAVE)){
             binding.tvNameTable.setText(AppConstants.TABLE + table.getStt());
             binding.tvNameTable.setCompoundDrawablesRelativeWithIntrinsicBounds(
                     ContextCompat.getDrawable(binding.getRoot().getContext(), R.drawable.ic_quan_ly_ban_24_brow),
                     null, null, null);
         }else {
             binding.tvNameTable.setText(AppConstants.TABLE + table.getStt());
             binding.tvNameTable.setCompoundDrawablesRelativeWithIntrinsicBounds(
                     ContextCompat.getDrawable(binding.getRoot().getContext(), R.drawable.ic_quan_ly_ban_24_black),
                     null, null, null);
         }
        }
    }
}
