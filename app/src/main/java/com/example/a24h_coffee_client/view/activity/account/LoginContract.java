package com.example.a24h_coffee_client.view.activity.account;

public interface LoginContract {
    interface View {
        void onMessage(String message);
        void onClick();
    }
    interface Presenter {
        void login(String userName, String password);
    }
}
