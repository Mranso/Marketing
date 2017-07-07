package com.control.marketing.model;

import java.io.Serializable;

/**
 * description:
 * author: yuantian
 * date: 2017/5/9
 * version: 2.0.0
 */

public class UserBean implements Serializable{

    private String id;
    private String name;
    private String headerIcon;
    private String lastMessage;
    private String lsatMessageTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeaderIcon() {
        return headerIcon;
    }

    public void setHeaderIcon(String headerIcon) {
        this.headerIcon = headerIcon;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLsatMessageTime() {
        return lsatMessageTime;
    }

    public void setLsatMessageTime(String lsatMessageTime) {
        this.lsatMessageTime = lsatMessageTime;
    }
}
