package com.example.a24h_coffee_client.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.databinding.ActivityMainBinding;
import com.example.a24h_coffee_client.view.fragment.home.HomeFragment;
import com.example.a24h_coffee_client.view.fragment.notification.NotificationFragment;
import com.example.a24h_coffee_client.view.fragment.setting.SettingsFragment;
import com.example.a24h_coffee_client.view.fragment.table.TableFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        switchFragment(new HomeFragment());
        onClick();
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick() {
        mBinding.bnvMain.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home:
                    switchFragment(new HomeFragment());
                    break;
                case R.id.menu_table:
                    switchFragment(new TableFragment());
                    break;
                case R.id.menu_notification:
                    switchFragment(new NotificationFragment());
                    break;
                case R.id.menu_setting:
                    switchFragment(new SettingsFragment());
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
}