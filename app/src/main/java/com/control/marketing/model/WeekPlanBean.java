package com.control.marketing.model;

import java.io.Serializable;
import java.util.List;

/**
 * description:
 * author: yuantian
 * date: 2017/6/18
 * version: 2.0.0
 */

public class WeekPlanBean implements Serializable {

    private String id;
    private List<TaskBean> weekPlans;
    private String weekTime;
    private String weekTitle;
    private String weekContent;
    private boolean isFinish;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TaskBean> getWeekPlans() {
        return weekPlans;
    }

    public void setWeekPlans(List<TaskBean> weekPlans) {
        this.weekPlans = weekPlans;
    }

    public String getWeekTime() {
        return weekTime;
    }

    public void setWeekTime(String weekTime) {
        this.weekTime = weekTime;
    }

    public String getWeekTitle() {
        return weekTitle;
    }

    public void setWeekTitle(String weekTitle) {
        this.weekTitle = weekTitle;
    }

    public String getWeekContent() {
        return weekContent;
    }

    public void setWeekContent(String weekContent) {
        this.weekContent = weekContent;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }
}
