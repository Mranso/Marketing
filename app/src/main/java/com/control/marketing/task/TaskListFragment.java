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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskListFragment extends BaseFragment {

    private Context context;
    private Spinner spinner;
    private TextView addTask;
    private ListView listView;

    private List<TaskBean> taskBeanList = new ArrayList<>();
    private TaskListAdapter taskListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.task_list_fragment_layout, container, false);
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
                AddTaskActivity.start(context);
            }
        });
    }

    private void initView(View view) {
        spinner = (Spinner) view.findViewById(R.id.task_list_spinner);
        addTask = (TextView) view.findViewById(R.id.task_list_add_task);
        listView = (ListView) view.findViewById(R.id.task_list_list_view);

        taskListAdapter = new TaskListAdapter(context);
        listView.setAdapter(taskListAdapter);
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

        taskListAdapter.refreshData(taskBeanList);
    }
}
