package com.control.marketing.task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.control.marketing.R;
import com.control.marketing.model.TaskBean;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: yuantian
 * date: 2017/5/21
 * version: 2.0.0
 */

public class TaskListAdapter extends BaseAdapter {

    private Context context;
    private List<TaskBean> taskBeanList = new ArrayList<>();

    public TaskListAdapter(Context context){
        this.context = context;
    }

    public void refreshData(List<TaskBean> taskBeanList){
        this.taskBeanList = taskBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return taskBeanList.size();
    }

    @Override
    public TaskBean getItem(int i) {
        return taskBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.task_list_item_layout, null);
        return view;
    }
}
