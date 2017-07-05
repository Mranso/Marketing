package com.control.marketing.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.control.marketing.R;
import com.control.marketing.common.BaseActivity;
import com.control.marketing.model.MessageBean;
import com.control.marketing.widget.TopBarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends BaseActivity {

    private Context context;
    private TopBarView topBarView;
    private ListView listView;
    private EditText editText;
    private Button sendButton;
    private List<MessageBean> messageBeanList = new ArrayList<>();
    private ChartListAdapter chartListAdapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, ChartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        EventBus.getDefault().register(this);
        context = this;
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        topBarView = (TopBarView) findViewById(R.id.chart_activity_top_bar);
        listView = (ListView) findViewById(R.id.chart_activity_list_view);
        editText = (EditText) findViewById(R.id.chart_activity_edit_view);
        sendButton = (Button) findViewById(R.id.chart_activity_send_message);
        initTopBar();
    }

    private void initTopBar() {
        topBarView.setTopBarTitle("朱俊铭");
        topBarView.setTopBarLeftVisibility(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageBean messageBean) {
        messageBeanList.add(messageBean);
        chartListAdapter.refreshData(messageBeanList);
        listView.setSelection(chartListAdapter.getCount() - 1);
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

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendContent = editText.getText().toString();
                if (sendContent.isEmpty()) {
                    Toast.makeText(context, "请输入文字", Toast.LENGTH_SHORT).show();
                    return;
                }
                MessageBean messageBean = new MessageBean();
                messageBean.setFromUser("111");
                messageBean.setMessageContent(sendContent);
                messageBean.setMessageType(2);
                messageBean.setMessageTime("2017-5-16");
                messageBean.setName("姓名");
                messageBean.setUserIcon("http://b.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=f0c5c08030d3d539c16807c70fb7c566/8ad4b31c8701a18bbef9f231982f07082838feba.jpg");
                messageBeanList.add(messageBean);
                chartListAdapter.refreshData(messageBeanList);
                listView.setSelection(chartListAdapter.getCount() - 1);
                editText.setText("");
            }
        });
    }

    private void initData() {
        messageBeanList.clear();
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 1) {
                MessageBean messageBean = new MessageBean();
                messageBean.setFromUser("111");
                messageBean.setMessageContent("你好，我是工作人员");
                messageBean.setMessageType(1);
                messageBean.setMessageTime("2017-5-16");
                messageBean.setName("姓名");
                messageBean.setUserIcon("http://b.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=f0c5c08030d3d539c16807c70fb7c566/8ad4b31c8701a18bbef9f231982f07082838feba.jpg");
                messageBeanList.add(messageBean);
            } else {
                MessageBean messageBean = new MessageBean();
                messageBean.setToUser("000");
                messageBean.setMessageContent("你好，我是用户");
                messageBean.setMessageType(2);
                messageBean.setMessageTime("2017-5-16");
                messageBean.setName("姓名");
                messageBean.setUserIcon("http://b.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=f0c5c08030d3d539c16807c70fb7c566/8ad4b31c8701a18bbef9f231982f07082838feba.jpg");
                messageBeanList.add(messageBean);
            }
        }

        chartListAdapter = new ChartListAdapter(context, messageBeanList);
        listView.setAdapter(chartListAdapter);
    }

}
