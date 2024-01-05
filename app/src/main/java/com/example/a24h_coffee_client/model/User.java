package com.example.a24h_coffee_client.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {
    @SerializedName("tenDangNhap")
    private String userName;
    @SerializedName("matKhau")
    private String password;
    @SerializedName("hoVaTen")
    private String name;
    @SerializedName("chucVu")
    private String role;
    @SerializedName("anhDaiDien")
    private String image;
    @SerializedName("dienThoai")
    private String phone;
    @SerializedName("ngaySinh")
    private Date date;
    @SerializedName("gioiTinh")
    private String sex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
