package com.example.a24h_coffee_client.view.fragment.setting;

import com.example.a24h_coffee_client.model.User;

import java.util.List;

public interface SettingContract {
    interface View{
        void updateUI(User user);
        void onMessage(String message, User user);
    }

    interface Presenter{
        void getUser(String userName);
    }
}

