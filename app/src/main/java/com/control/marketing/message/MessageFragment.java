package com.control.marketing.message;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.control.marketing.R;
import com.control.marketing.common.BaseFragment;
import com.control.marketing.model.UserBean;
import com.control.marketing.model.UserInfoMessage;
import com.control.marketing.widget.TopBarView;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends BaseFragment {

    private Context context;
    private TopBarView topBarView;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MessageListAdapter messageListAdapter;
    private List<UserBean> userBeanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        initView(view);
        initListener();
        initData();
    }

    private void initView(View view) {
        topBarView = (TopBarView) view.findViewById(R.id.message_fragment_top_bar);
        topBarView.setTopBarTitle("聊天");

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.message_fragment_swipe_refresh);
        listView = (ListView) view.findViewById(R.id.message_fragment_list_view);
        messageListAdapter = new MessageListAdapter(context);
        listView.setAdapter(messageListAdapter);
    }

    private void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChartActivity.start(context, userBeanList.get(i));
            }
        });
    }

    private void initData() {
        UserBean userBean = new UserBean();
        if("111111".equals(UserInfoMessage.getUserId())){
            userBean.setName("用户二");
            userBean.setHeaderIcon("http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg");
        }else {
            userBean.setName(UserInfoMessage.getUserName());
            userBean.setHeaderIcon(UserInfoMessage.getUserIcon());
        }
        userBean.setLastMessage("你好，这是最后一条消息");
        userBean.setLsatMessageTime("2017-5-10");
        userBeanList.add(userBean);
        swipeRefreshLayout.setRefreshing(false);
        messageListAdapter.refreshData(userBeanList);
    }
}
