package com.control.marketing.task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.marketing.R;
import com.control.marketing.model.TaskBean;

import java.util.ArrayList;
import java.util.List;

import static com.control.marketing.R.id.task_list_item_progress_bar;

/**
 * description:
 * author: yuantian
 * date: 2017/5/21
 * version: 2.0.0
 */

public class TaskScheduleAdapter extends BaseAdapter {

    private Context context;
    private List<TaskBean> taskBeanList = new ArrayList<>();

    public TaskScheduleAdapter(Context context){
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
    public Object getItem(int i) {
        return taskBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TaskListItemViewHolder taskListItemViewHolder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.task_schedule_item_layout, viewGroup, false);
            taskListItemViewHolder = new TaskListItemViewHolder();
            taskListItemViewHolder.progressBar = (TextView) view.findViewById(task_list_item_progress_bar);
            taskListItemViewHolder.progressStatusImage = (ImageView) view.findViewById(R.id.task_list_item_progress_status);
            view.setTag(taskListItemViewHolder);
        }else {
            taskListItemViewHolder = (TaskListItemViewHolder) view.getTag();
        }

        if(i == 0){
            taskListItemViewHolder.progressBar.setVisibility(View.INVISIBLE);
            taskListItemViewHolder.progressStatusImage.setBackgroundResource(R.drawable.task_list_item_status_no);
        }else {
            taskListItemViewHolder.progressBar.setVisibility(View.VISIBLE);
            taskListItemViewHolder.progressStatusImage.setBackgroundResource(R.drawable.task_list_item_status_yes);
        }

        return view;
    }

    private class TaskListItemViewHolder{
        private TextView progressBar;
        private ImageView progressStatusImage;
    }
}
