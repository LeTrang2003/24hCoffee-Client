package com.example.a24h_coffee_client.view.activity.updateinfor;

import android.net.Uri;

import com.example.a24h_coffee_client.model.User;

import java.util.Date;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public interface UpdateAccountContract {

    interface View {
        void onMessage(String message);
        void onUser(User user);
    }

    interface Presenter {
        void updateInFor(String userName, String name,String image,String phone,String dateOfBirth,String sex);
        void updateInForFile(RequestBody requestBodyUserName, RequestBody requestBodyName, MultipartBody.Part image, RequestBody requestBodyPhone, RequestBody requestBodyDate, RequestBody requestBodySex);
    }
}
