package com.control.marketing.task;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.control.marketing.R;
import com.control.marketing.common.BaseFragment;
import com.control.marketing.model.UserBean;

import java.util.ArrayList;
import java.util.List;

public class TaskTopFragment extends BaseFragment {

    private Context context;
    private ListView listView;
    private TaskTopAdapter taskTopAdapter;
    private List<UserBean> userBeenList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.task_top_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        initView(view);
        initData();
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.task_top_list_view);
        taskTopAdapter = new TaskTopAdapter(context);
        listView.setAdapter(taskTopAdapter);
    }

    private void initData() {
        for (int i = 0; i < 50; i++) {
            UserBean userBean = new UserBean();
            userBean.setName("朱俊铭");
            userBean.setHeaderIcon("http://b.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=f0c5c08030d3d539c16807c70fb7c566/8ad4b31c8701a18bbef9f231982f07082838feba.jpg");
            userBean.setLastMessage("你好，这是最后一条消息");
            userBean.setLsatMessageTime("2017-5-10");
            userBeenList.add(userBean);
        }
        taskTopAdapter.refreshData(userBeenList);
    }
}
