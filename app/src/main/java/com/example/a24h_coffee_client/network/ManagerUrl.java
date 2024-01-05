package com.example.a24h_coffee_client.network;

public class ManagerUrl {

    public static final String BASE_URL = "http://172.20.10.5:3000/api/";

    // account
    public static final String LOGIN = "login";

    //user
    public static final String UPDATE_INF_USER = "update-user";
    public static final String READ_USER = "read-user";

    // setting
    public static final String CHANGE_PASS = "update/password/user";

    // table
    public static final String READ_TABLE = "tables";

    // product
    public static final String READ_PRODUCT = "products";

    // banner
    public static final String READ_BANNER = "banners";

    // category
    public static final String READ_CATEGORY = "category";
    // bill
    public static final String INSERT_BILL = "insert/bill";
    public static final String UPDATE_BILL = "update/bill";
    // notification
    public static final String READ_NOTIFICATION = "notifications";
    public static final String INSERT_NOTIFICATION = "insert/notification";
    public static final String UPDATE_NOTIFICATION = "update/notification";
    public static final String DELETE_NOTIFICATION = "delete/notification/{id}";

    // bill detail
    public static final String READ_TABLE_BILL = "table/bill/{tableID}";
    public static final String READ_DETAIL_BILL = "detail/bills/{billID}";
    public static final String INSERT_BILL_DETAIL = "insert/bill/detail";
}
