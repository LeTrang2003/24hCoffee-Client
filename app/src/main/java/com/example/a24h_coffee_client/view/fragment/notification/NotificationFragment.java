package com.example.a24h_coffee_client.view.fragment.notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.adapter.AdapterNotification;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.databinding.FragmentNotificationBinding;
import com.example.a24h_coffee_client.model.Notification;
import com.example.a24h_coffee_client.utils.SwipeToDeleteCallback;
import com.example.a24h_coffee_client.utils.UIUtils;

import java.util.List;

public class NotificationFragment extends Fragment implements NotificationContract.View {
    private FragmentNotificationBinding mBinding;
    private NotificationContract.Presenter mPresenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new NotificationPresenter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentNotificationBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UIUtils.openLayout(mBinding.ivLoadingNotificationFragment, mBinding.layoutNotificationFragment, false, getContext());
        mPresenter.getListNotification(getUsername());
    }

    public String getUsername() {
        SharedPreferences prefs = getContext().getSharedPreferences(AppConstants.PREFS_NAME, Context.MODE_PRIVATE);
        prefs.getString(AppConstants.KEY_USERNAME, "");
        return prefs.getString(AppConstants.KEY_USERNAME, "");
    }

    @Override
    public void onListNotification(List<Notification> notifications) {
        mBinding.tvShowNotification.setVisibility(View.GONE);
        AdapterNotification adapterNotification = new AdapterNotification(notifications, mPresenter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rcvNotification.setLayoutManager(layoutManager);
        mBinding.rcvNotification.setAdapter(adapterNotification);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapterNotification, mBinding.tvShowNotification));
        itemTouchHelper.attachToRecyclerView(mBinding.rcvNotification);
        mPresenter.updateNotification(getUsername());
        new Handler().postDelayed(() -> {
            UIUtils.openLayout(mBinding.ivLoadingNotificationFragment, mBinding.layoutNotificationFragment, true, getContext());
        }, 500);
    }
}