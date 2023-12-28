package com.example.a24h_coffee_client.view.activity.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {
    private ActivitySearchBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }
}