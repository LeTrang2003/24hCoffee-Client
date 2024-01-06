package com.example.a24h_coffee_client.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.databinding.ActivityMainBinding;
import com.example.a24h_coffee_client.model.Notification;
import com.example.a24h_coffee_client.network.ApiClient;
import com.example.a24h_coffee_client.network.ApiService;
import com.example.a24h_coffee_client.view.fragment.home.HomeFragment;
import com.example.a24h_coffee_client.view.fragment.notification.NotificationFragment;
import com.example.a24h_coffee_client.view.fragment.notification.ResponseNotification;
import com.example.a24h_coffee_client.view.fragment.setting.SettingsFragment;
import com.example.a24h_coffee_client.view.fragment.table.TableFragment;
import com.google.android.material.badge.BadgeDrawable;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.tvBadge.setVisibility(View.GONE);
        switchFragment(new HomeFragment());
        onClick();
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick() {
        mBinding.bnvMain.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home:
                    switchFragment(new HomeFragment());
                    getListNotification(getUsername());
                    break;
                case R.id.menu_table:
                    switchFragment(new TableFragment());
                    getListNotification(getUsername());
                    break;
                case R.id.menu_notification:
                    switchFragment(new NotificationFragment());
                    mBinding.tvBadge.setVisibility(View.GONE);
                    break;
                case R.id.menu_setting:
                    switchFragment(new SettingsFragment());
                    getListNotification(getUsername());
                    break;
            }

            return true;
        });
    }

    public void switchFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    public String getUsername() {
        SharedPreferences prefs = getSharedPreferences(AppConstants.PREFS_NAME, Context.MODE_PRIVATE);
        prefs.getString(AppConstants.KEY_USERNAME, "");
        return prefs.getString(AppConstants.KEY_USERNAME, "");
    }

    public void getListNotification(String userId) {
        ApiClient.getClient().create(ApiService.class).readNotification(userId).enqueue(new Callback<ResponseNotification>() {
            @Override
            public void onResponse(@NonNull Call<ResponseNotification> call, @NonNull Response<ResponseNotification> response) {
                if (response.isSuccessful()){
                    if (AppConstants.SUCCESS.equals(Objects.requireNonNull(response.body()).getStatus())){
                        checkStatusSee(response.body().getNotifications());
                    }else {
                        Log.d("ER", "error");
                    }
                }else {
                    Log.d("ER", "error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseNotification> call, @NonNull Throwable t) {

            }
        });
    }

    private void checkStatusSee(List<Notification> notifications) {
       for (Notification notification : notifications){
           if (notification.getStatusSee().equals("Chưa xem")){
               setBadgerNotification(true);
               return;
           }else {
               setBadgerNotification(false);
           }
       }
    }

    private void setBadgerNotification(boolean check) {
//        BadgeDrawable badgeDrawable = mBinding.bnvMain.getOrCreateBadge(R.id.menu_home);
//        badgeDrawable.setVisible(true);
//        badgeDrawable.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        if (check){
            mBinding.tvBadge.setVisibility(View.VISIBLE);
        }else {
            mBinding.tvBadge.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        getListNotification(getUsername());
    }
}