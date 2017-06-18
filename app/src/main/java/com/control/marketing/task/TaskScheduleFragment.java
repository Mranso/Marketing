package com.control.marketing.task;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.control.marketing.R;
import com.control.marketing.common.BaseFragment;
import com.control.marketing.model.TaskBean;
import com.control.marketing.task.everydayplan.AddTaskActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskScheduleFragment extends BaseFragment {

    private Context context;
    private Spinner spinner;
    private ListView listView;
    private TextView addTask;

    private List<TaskBean> taskBeanList = new ArrayList<>();
    private TaskScheduleAdapter taskScheduleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.task_schedule_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        initView(view);
        initData();
        initListener();
    }

    private void initListener() {
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTaskActivity.startForResult(getActivity(), 201);
            }
        });
    }

    private void initView(View view) {
        spinner = (Spinner) view.findViewById(R.id.task_list_spinner);
        listView = (ListView) view.findViewById(R.id.task_list_list_view);
        addTask = (TextView) view.findViewById(R.id.task_schedule_add_task);
        taskScheduleAdapter = new TaskScheduleAdapter(context);
        listView.setAdapter(taskScheduleAdapter);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            TaskBean taskBean = new TaskBean();
            taskBean.setTitle("这是一个任务的标题");
            taskBean.setContent("这是一个任务的内容，这是一个任务的内容,这是一个任务的内容,这是一个任务的内容,这是一个任务的内容");
            taskBean.setImages(new ArrayList<>(Arrays.asList("http://tupian.enterdesk.com/2014/lxy/2014/12/02/7/2.jpg","http://tupian.enterdesk.com/2014/lxy/2014/12/02/7/2.jpg","http://tupian.enterdesk.com/2014/lxy/2014/12/02/7/2.jpg")));
            taskBean.setTime("2017-5-10");
            taskBeanList.add(taskBean);
        }

        taskScheduleAdapter.refreshData(taskBeanList);
    }
}
