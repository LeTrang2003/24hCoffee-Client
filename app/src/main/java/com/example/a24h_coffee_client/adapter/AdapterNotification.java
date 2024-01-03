package com.example.a24h_coffee_client.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.databinding.ItemNotifitationBinding;
import com.example.a24h_coffee_client.databinding.ItemTableBinding;
import com.example.a24h_coffee_client.model.Notification;
import com.example.a24h_coffee_client.model.Table;
import com.example.a24h_coffee_client.utils.FormatUtils;
import com.example.a24h_coffee_client.view.fragment.table.TableContract;

import java.util.List;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.viewHolder> {
    private final List<Notification> mList;


    public AdapterNotification(List<Notification> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotifitationBinding mBinding = ItemNotifitationBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new viewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Notification notification = mList.get(position);
        if (notification == null){
            return;
        }
        holder.bind(notification);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private final ItemNotifitationBinding binding;

        public viewHolder(ItemNotifitationBinding  binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(Notification notification) {
            binding.tvNoiDung.setText(notification.getContext());
            binding.tvNgayThongBao.setText(FormatUtils.formatDate(notification.getDateCreate()));
        }
    }
}
