package com.control.marketing.model;

import com.control.marketing.utils.SharedPreferencesUtils;

/**
 * description:
 * author: yuantian
 * date: 2017/7/6
 * version: 2.0.0
 */

public class UserInfoMessage {

    private static final String USER_INFO_USER_NAME = "USER_NAME";
    private static final String USER_INFO_USER_ID = "USER_ID";
    private static final String USER_INFO_USER_ICON = "USER_ICON";

    public static void cacheUserInfo(UserBean userBean){
        SharedPreferencesUtils.getInstance().putString(USER_INFO_USER_NAME, userBean.getName());
        SharedPreferencesUtils.getInstance().putString(USER_INFO_USER_ID, userBean.getId());
        SharedPreferencesUtils.getInstance().putString(USER_INFO_USER_ICON, userBean.getHeaderIcon());
    }

    public static String getUserName(){
        return SharedPreferencesUtils.getInstance().getString(USER_INFO_USER_NAME, "");
    }

    public static String getUserId(){
        return SharedPreferencesUtils.getInstance().getString(USER_INFO_USER_ID, "");
    }

    public static String getUserIcon(){
        return SharedPreferencesUtils.getInstance().getString(USER_INFO_USER_ICON, "");
    }

    public static UserBean getUserInfo(){
        UserBean userBean = new UserBean();
        userBean.setName(getUserName());
        userBean.setId(getUserId());
        userBean.setHeaderIcon(getUserIcon());
        return userBean;
    }
}
