package com.example.a24h_coffee_client.view.activity.account;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.databinding.ActivityLoginBinding;
import com.example.a24h_coffee_client.model.User;
import com.example.a24h_coffee_client.utils.ValidateUtils;
import com.example.a24h_coffee_client.view.activity.MainActivity;
import com.example.a24h_coffee_client.view.activity.SplashActivity;
import com.google.gson.Gson;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private ActivityLoginBinding mBinding;
    private LoginContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mPresenter = new LoginPresenter(this);
        onClick();
    }

    @Override
    public void onClick() {
        mBinding.btnSignIn.setOnClickListener(view -> {
            if (!ValidateUtils.validateLoginIsEmpty(Objects.requireNonNull(mBinding.etUserName.getText()).toString(), Objects.requireNonNull(mBinding.etPassword.getText()).toString())){
                Toast.makeText(this, AppConstants.ENTER_INFO, Toast.LENGTH_SHORT).show();
                return;
            }
            mProgressDialog = ProgressDialog.show(this, "", "Đăng nhập");
            mPresenter.login(mBinding.etUserName.getText().toString(), mBinding.etPassword.getText().toString());
        });
    }

    @Override
    public void onMessage(String message, User user) {
        if (AppConstants.SUCCESS.equals(message)){
            String userJson = new Gson().toJson(user);
            SharedPreferences.Editor editor = this.getSharedPreferences(AppConstants.PREFS_NAME, Context.MODE_PRIVATE).edit();
            editor.putString(AppConstants.KEY_USERNAME, user.getUserName());
            editor.putString(AppConstants.KEY_USER, userJson);
            editor.apply();

            new Handler().postDelayed(() -> {
                mProgressDialog.dismiss();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }, 1000);

        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}