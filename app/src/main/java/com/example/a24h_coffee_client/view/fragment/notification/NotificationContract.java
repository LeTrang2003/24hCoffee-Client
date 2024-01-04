package com.example.a24h_coffee_client.view.fragment.notification;

import com.example.a24h_coffee_client.model.Notification;

import java.util.List;

public interface NotificationContract {
    interface View {
        void onListNotification(List<Notification> notifications);
    }

    interface Presenter {
        void getListNotification(String userId);
        void updateNotification(String userId);
        void deleteNotification(int id);
    }
}
