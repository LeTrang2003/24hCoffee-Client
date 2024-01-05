package com.example.a24h_coffee_client.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.example.a24h_coffee_client.constant.AppConstants;
import com.example.a24h_coffee_client.model.User;
import com.google.gson.Gson;

public class ValidateUtils {

    public static boolean validateLoginIsEmpty(String email, String pass){
        return !email.isEmpty() && !pass.isEmpty();
    }
    public static boolean validateUpdateAccountIsEmpty(String trim, String trim1, String trim2, String trim3){
        return !trim.isEmpty() && !trim1.isEmpty()&&!trim2.isEmpty() && !trim3.isEmpty();
    }

//    public static boolean validateForgotPassIsEmpty(String email){
//        return !email.isEmpty();
//    }
//
//    public static boolean validateRegisterIsEmpty(String email, String pass, String againPass){
//        return !email.isEmpty() && !pass.isEmpty() && !againPass.isEmpty();
//    }
//    public static boolean validateRegisterEqual(String pass, String againPass){
//        return againPass.equals(pass);
//    }
    public static boolean isDataInputEmpty(String... data) {
        for (String str : data) {
            if ( str == null || str.isEmpty()){
                return true;
            }
        }
        return false;
    }
    public static boolean validateChangePasswordIsEmpty(String password, String passwordNew, String passwordNewAgain){
         return !password.isEmpty() && !passwordNew.isEmpty() && !passwordNewAgain.isEmpty();
    }

    public static boolean validateChangePasswordEqual(String passwordNew, String passwordNewAgain){
        return passwordNewAgain.equals(passwordNew);
    }

    public static boolean validatePasswordEqual(String pass, String passwordNew){
        return pass.equals(passwordNew);
    }



    /**
     * Kiểm tra tính hợp lệ của mật khẩu.
     *
     * @param password Mật khẩu cần kiểm tra.
     * @return `true` nếu mật khẩu hợp lệ, ngược lại trả về `false`.
     */
    public static boolean isPasswordValid(String password, Context context) {
        return password.equals(getUsername(context));
    }

    public static String getUsername(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(AppConstants.PREFS_NAME, Context.MODE_PRIVATE);
        String strUser = prefs.getString(AppConstants.KEY_USER, "");
        return new Gson().fromJson(strUser, User.class).getPassword();
    }
}
