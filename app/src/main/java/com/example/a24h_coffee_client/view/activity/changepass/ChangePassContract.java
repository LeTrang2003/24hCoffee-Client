package com.example.a24h_coffee_client.view.activity.changepass;

import android.content.Context;

import com.example.a24h_coffee_client.model.User;

public interface ChangePassContract {
    interface View {
        void onMessage(String message);
        void onUser(User user);
    }
    interface Presenter {
        void changePassword(String passwordAgain, String userId);

        boolean doChange(String password, String passNew, String passAgain, Context context);
    }
}
