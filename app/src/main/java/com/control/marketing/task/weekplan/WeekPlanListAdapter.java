package com.control.marketing.task.weekplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.marketing.R;
import com.control.marketing.model.TaskBean;
import com.control.marketing.model.WeekPlanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: yuantian
 * date: 2017/5/21
 * version: 2.0.0
 */

public class WeekPlanListAdapter extends BaseAdapter {

    private Context context;
    private List<WeekPlanBean> weekPlanBeanList = new ArrayList<>();

    public WeekPlanListAdapter(Context context) {
        this.context = context;
    }

    public void refreshData(List<WeekPlanBean> weekPlanBeanList) {
        this.weekPlanBeanList = weekPlanBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return weekPlanBeanList.size();
    }

    @Override
    public WeekPlanBean getItem(int i) {
        return weekPlanBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TaskListItemViewHolder taskListItemViewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.week_plan_list_item_layout, viewGroup, false);
            taskListItemViewHolder = new TaskListItemViewHolder();
            taskListItemViewHolder.taskTime = (TextView) view.findViewById(R.id.week_plan_item_time);
            taskListItemViewHolder.taskStatus = (ImageView) view.findViewById(R.id.week_plan_item_status);
            taskListItemViewHolder.taskTitle = (TextView) view.findViewById(R.id.week_plan_item_title);
            taskListItemViewHolder.taskContentOne = (TextView) view.findViewById(R.id.week_plan_item_one_title);
            taskListItemViewHolder.taskContentTwo = (TextView) view.findViewById(R.id.week_plan_item_two_title);
            taskListItemViewHolder.taskContentThree = (TextView) view.findViewById(R.id.week_plan_item_three_title);
            taskListItemViewHolder.taskContentFour = (TextView) view.findViewById(R.id.week_plan_item_four_title);
            taskListItemViewHolder.taskContentFive = (TextView) view.findViewById(R.id.week_plan_item_five_title);
            view.setTag(taskListItemViewHolder);
        } else {
            taskListItemViewHolder = (TaskListItemViewHolder) view.getTag();
        }
        WeekPlanBean taskBean = weekPlanBeanList.get(i);
        taskListItemViewHolder.taskTime.setText(taskBean.getWeekTime());
        taskListItemViewHolder.taskTitle.setText(taskBean.getWeekTitle());
        taskListItemViewHolder.taskContentOne.setText(taskBean.getWeekPlans().get(0).getTitle());
        taskListItemViewHolder.taskContentTwo.setText(taskBean.getWeekPlans().get(1).getTitle());
        taskListItemViewHolder.taskContentThree.setText(taskBean.getWeekPlans().get(2).getTitle());
        taskListItemViewHolder.taskContentFour.setText(taskBean.getWeekPlans().get(3).getTitle());
        taskListItemViewHolder.taskContentFive.setText(taskBean.getWeekPlans().get(4).getTitle());

        if (taskBean.isFinish()) {
            taskListItemViewHolder.taskStatus.setBackgroundResource(R.drawable.task_list_item_status_yes);
        } else {
            taskListItemViewHolder.taskStatus.setBackgroundResource(R.drawable.task_list_item_status_no);
        }
        return view;
    }

    private class TaskListItemViewHolder {
        private TextView taskTime;
        private ImageView taskStatus;
        private TextView taskTitle;
        private TextView taskContentOne;
        private TextView taskContentTwo;
        private TextView taskContentThree;
        private TextView taskContentFour;
        private TextView taskContentFive;
    }
}
