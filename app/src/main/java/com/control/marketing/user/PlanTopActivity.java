package com.control.marketing.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.control.marketing.R;
import com.control.marketing.common.BaseActivity;
import com.control.marketing.model.UserBean;
import com.control.marketing.widget.TopBarView;

import java.util.ArrayList;
import java.util.List;

public class PlanTopActivity extends BaseActivity {

    private Context context;
    private ListView listView;
    private TopBarView topBarView;
    private PlanTopAdapter planTopAdapter;
    private List<UserBean> userBeenList = new ArrayList<>();

    public static void start(Context context){
        Intent intent = new Intent(context, PlanTopActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_top_fragment_layout);
        context = this;
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        topBarView.setOnTopBarClickListener(new TopBarView.TopBarClickListener() {
            @Override
            public void leftClickListener() {
                finish();
            }

            @Override
            public void rightClickListener() {

            }
        });
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.task_top_list_view);
        topBarView = (TopBarView) findViewById(R.id.plan_top_top_bar);
        topBarView.setTopBarLeftVisibility(true);
        topBarView.setTopBarTitle("计划排名");
        planTopAdapter = new PlanTopAdapter(context);
        listView.setAdapter(planTopAdapter);
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
        planTopAdapter.refreshData(userBeenList);
    }
}
