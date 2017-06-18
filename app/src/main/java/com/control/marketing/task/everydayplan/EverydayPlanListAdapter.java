package com.control.marketing.task.everydayplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.marketing.R;
import com.control.marketing.model.TaskBean;
import com.control.marketing.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: yuantian
 * date: 2017/5/21
 * version: 2.0.0
 */

public class EverydayPlanListAdapter extends BaseAdapter {

    private Context context;
    private List<TaskBean> taskBeanList = new ArrayList<>();

    public EverydayPlanListAdapter(Context context) {
        this.context = context;
    }

    public void refreshData(List<TaskBean> taskBeanList) {
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
        TaskListItemViewHolder taskListItemViewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.task_list_item_layout, viewGroup, false);
            taskListItemViewHolder = new TaskListItemViewHolder();
            taskListItemViewHolder.taskTime = (TextView) view.findViewById(R.id.task_list_item_time);
            taskListItemViewHolder.taskStatus = (ImageView) view.findViewById(R.id.task_list_item_status);
            taskListItemViewHolder.taskTitle = (TextView) view.findViewById(R.id.task_list_item_title);
            taskListItemViewHolder.taskContent = (TextView) view.findViewById(R.id.task_list_item_content);
            taskListItemViewHolder.taskImage = (ImageView) view.findViewById(R.id.task_list_item_image);
            view.setTag(taskListItemViewHolder);
        } else {
            taskListItemViewHolder = (TaskListItemViewHolder) view.getTag();
        }
        TaskBean taskBean = taskBeanList.get(i);
        taskListItemViewHolder.taskTime.setText(taskBean.getTime());
        taskListItemViewHolder.taskTitle.setText(taskBean.getTitle());
        taskListItemViewHolder.taskContent.setText(taskBean.getContent());
        if (taskBean.getImages().size() > 0) {
            taskListItemViewHolder.taskImage.setVisibility(View.VISIBLE);
            ImageUtils.setImage(context, taskBean.getImages().get(0), taskListItemViewHolder.taskImage);
        } else {
            taskListItemViewHolder.taskImage.setVisibility(View.GONE);
        }

        if(taskBean.isFinish()){
            taskListItemViewHolder.taskStatus.setBackgroundResource(R.drawable.task_list_item_status_yes);
        }else {
            taskListItemViewHolder.taskStatus.setBackgroundResource(R.drawable.task_list_item_status_no);
        }

        return view;
    }

    private class TaskListItemViewHolder {
        private TextView taskTime;
        private ImageView taskStatus;
        private TextView taskTitle;
        private TextView taskContent;
        private ImageView taskImage;
    }
}
