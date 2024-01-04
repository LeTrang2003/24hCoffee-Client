package com.example.a24h_coffee_client.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a24h_coffee_client.databinding.ItemBannerBinding;
import com.example.a24h_coffee_client.databinding.ItemBillDetailBinding;
import com.example.a24h_coffee_client.model.Banner;
import com.example.a24h_coffee_client.model.BillDetail;
import com.example.a24h_coffee_client.utils.FormatUtils;

import java.util.List;

public class AdapterBillDetail extends RecyclerView.Adapter<AdapterBillDetail.viewHolder> {
    private final List<BillDetail> mList;

    public AdapterBillDetail(List<BillDetail> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBillDetailBinding mBinding = ItemBillDetailBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new viewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        BillDetail billDetail = mList.get(position);
        if (billDetail == null){
            return;
        }
        holder.bind(billDetail);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private final ItemBillDetailBinding binding;

        public viewHolder(ItemBillDetailBinding  binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(BillDetail billDetail) {
          Glide.with(binding.getRoot()).load(billDetail.getImageProduct()).centerCrop().into(binding.ivProductBillr);
          binding.tvNameProductIvProductBillDetail.setText(billDetail.getNameProduct());
          binding.tvPriceBillDetail.setText(FormatUtils.formatCurrency(billDetail.getPriceProduct()));
          binding.tvQuantityBillDetail.setText("x"+billDetail.getQuantityProduct());
        }
    }
}
