package com.example.a24h_coffee_client.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.databinding.ItemTableBinding;
import com.example.a24h_coffee_client.model.Table;
import com.example.a24h_coffee_client.utils.ItemClickUtils;
import com.example.a24h_coffee_client.view.activity.table.TableDetailContract;
import com.example.a24h_coffee_client.view.fragment.table.TableContract;

import java.util.List;

public class AdapterTableBT extends RecyclerView.Adapter<AdapterTableBT.viewHolder> {
    private final List<Table> mList;
    private final TableDetailContract.View mView;
    private final ItemClickUtils itemClickUtils;
    private int selectItem = -1;

    public AdapterTableBT(List<Table> mList, TableDetailContract.View mView, ItemClickUtils itemClickUtils) {
        this.mList = mList;
        this.mView = mView;
        this.itemClickUtils = itemClickUtils;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTableBinding mBinding = ItemTableBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new viewHolder(mBinding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Table table = mList.get(position);

        if (table == null){
            return;
        }

        holder.bind(table);

        holder.binding.btnTypeProduct.setOnClickListener(view -> {
            itemClickUtils.onItemClick(table);
            selectItem = holder.getAdapterPosition();
            notifyDataSetChanged();
        });

//      item được chọn
        int drawableResId = (position == selectItem) ? R.drawable.ic_quan_ly_ban_24_brow : R.drawable.ic_quan_ly_ban_24_black;
        holder.binding.tvNameTable.setCompoundDrawablesRelativeWithIntrinsicBounds(
                ContextCompat.getDrawable(holder.binding.getRoot().getContext(), drawableResId), null, null, null);
        holder.binding.btnTypeProduct.setBackgroundResource(
                position == selectItem ? R.color.Gray1Primary : R.color.white
        );
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
             binding.tvNameTable.setText(AppConstants.TABLE + table.getStt());
             binding.tvNameTable.setCompoundDrawablesRelativeWithIntrinsicBounds(
                     ContextCompat.getDrawable(binding.getRoot().getContext(), R.drawable.ic_quan_ly_ban_24_black),
                     null, null, null);
        }
    }
}
