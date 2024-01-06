package com.example.a24h_coffee_client.utils;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.a24h_coffee_client.R;

public class UIUtils {
    public static void openLayout(ImageView imageView, FrameLayout frameLayout, boolean check, Context context){
        if (check){
            imageView.setVisibility(View.GONE);
            frameLayout.setVisibility(View.GONE);
        }else {
            Glide.with(context).load("https://i.gifer.com/origin/f5/f5baef4b6b6677020ab8d091ef78a3bc_w200.webp").into(imageView);
            frameLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.Gray1Primary));
        }
    }
}
