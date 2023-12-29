package com.example.a24h_coffee_client.view.activity.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.databinding.ActivityContactBinding;

public class ContactActivity extends AppCompatActivity {
    private ActivityContactBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.ivBackContact.setOnClickListener(view -> onBackPressed());
    }
}