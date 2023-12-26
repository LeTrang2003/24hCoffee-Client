package com.example.a24h_coffee_client.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.view.activity.account.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(() -> startActivity(new Intent(SplashActivity.this, LoginActivity.class)), 3000);
    }
}