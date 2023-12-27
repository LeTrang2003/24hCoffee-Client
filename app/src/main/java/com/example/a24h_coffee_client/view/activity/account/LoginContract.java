package com.example.a24h_coffee_client.view.activity.account;

import com.example.a24h_coffee_client.model.User;

public interface LoginContract {
    interface View {
        void onMessage(String message, User user);
        void onClick();
    }
    interface Presenter {
        void login(String userName, String password);
    }
}
