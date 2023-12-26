package com.example.a24h_coffee_client.view.activity.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.databinding.ActivityLoginBinding;
import com.example.a24h_coffee_client.utils.ValidateUtils;
import com.example.a24h_coffee_client.view.activity.MainActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private ActivityLoginBinding mBinding;
    private LoginContract.Presenter mPresenter;
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
            mPresenter.login(mBinding.etUserName.getText().toString(), mBinding.etPassword.getText().toString());
        });
    }

    @Override
    public void onMessage(String message) {
        if (AppConstants.SUCCESS.equals(message)){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}