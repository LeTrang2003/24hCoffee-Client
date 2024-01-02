package com.example.a24h_coffee_client.view.activity.changepass;

public interface ChangePassContract {
    interface View {
        void onMessage(String message);
    }
    interface Presenter {
        void changePassword(String passwordAgain);
        void getUser();

        boolean doChange(String password, String passNew, String passAgain);
    }
}
