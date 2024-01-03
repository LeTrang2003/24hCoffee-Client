package com.example.a24h_coffee_client.view.fragment.notification;

import com.example.a24h_coffee_client.model.Notification;

import java.util.List;

public class ResponseNotification {
    private String status;
    private List<Notification> notifications;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
