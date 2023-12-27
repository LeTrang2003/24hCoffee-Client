package com.example.a24h_coffee_client.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.view.activity.account.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkLogin();
    }

    private void checkLogin() {
        if (getUsername(this).isEmpty()){
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }, 2000);
        }else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }

    // Lấy giá trị từ SharedPreferences
    public String getUsername(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(AppConstants.PREFS_NAME, Context.MODE_PRIVATE);
        prefs.getString(AppConstants.KEY_USERNAME, "");
        return prefs.getString(AppConstants.KEY_USERNAME, "");
    }
}