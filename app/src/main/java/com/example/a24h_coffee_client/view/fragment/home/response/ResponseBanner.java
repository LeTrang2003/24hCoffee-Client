package com.example.a24h_coffee_client.view.fragment.home.response;

import com.example.a24h_coffee_client.model.Banner;

import java.util.List;

public class ResponseBanner {
    private String status;
    private List<Banner> banners;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }
}
