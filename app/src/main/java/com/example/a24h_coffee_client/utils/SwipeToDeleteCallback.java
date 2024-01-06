package com.example.a24h_coffee_client.utils;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a24h_coffee_client.adapter.AdapterNotification;

public class SwipeToDeleteCallback extends ItemTouchHelper.Callback {

    private final AdapterNotification mAdapter;
    private final TextView tv;

    public SwipeToDeleteCallback(AdapterNotification adapter, TextView tv) {
        mAdapter = adapter;
        this.tv = tv;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        mAdapter.deleteItem(position);
        if (mAdapter.getListSize() == 0 ){
            tv.setVisibility(View.VISIBLE);
        }
    }
}

