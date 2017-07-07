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
import com.control.marketing.model.UserBean;
import com.control.marketing.model.UserInfoMessage;
import com.control.marketing.utils.TimeUtils;
import com.control.marketing.widget.TopBarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

public class ChartActivity extends BaseActivity {

    private static final String INTENT_KEY_USER_BEAN = "INTENT_KEY_USER_BEAN";
    private Context context;
    private TopBarView topBarView;
    private ListView listView;
    private EditText editText;
    private Button sendButton;
    private List<MessageBean> messageBeanList = new ArrayList<>();
    private ChartListAdapter chartListAdapter;
    private UserBean toUserBean;

    public static void start(Context context, UserBean userBean) {
        Intent intent = new Intent(context, ChartActivity.class);
        intent.putExtra(INTENT_KEY_USER_BEAN, userBean);
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
        topBarView.setTopBarLeftVisibility(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageBean messageBean) {
        messageBean.setName(toUserBean.getName());
        messageBean.setUserIcon(toUserBean.getHeaderIcon());
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
                if ("111111".equals(UserInfoMessage.getUserId())) {
                    sendTextMessage(sendContent, "222222");
                } else {
                    sendTextMessage(sendContent, "111111");
                }
            }
        });
    }

    private void sendTextMessage(final String messageContent, final String toUserId) {
        TextMessage textMessage = TextMessage.obtain(messageContent);
        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, toUserId, textMessage, null, null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(Message message) {
                // 消息成功存到本地数据库的回调
            }

            @Override
            public void onSuccess(Message message) {
                // 消息发送成功的回调
                MessageBean messageBean = new MessageBean();
                messageBean.setFromUser(UserInfoMessage.getUserId());
                messageBean.setToUser(toUserId);
                messageBean.setMessageContent(messageContent);
                messageBean.setMessageType(2);
                messageBean.setMessageTime(TimeUtils.getDateString(message.getSentTime()));
                messageBean.setName(UserInfoMessage.getUserName());
                messageBean.setUserIcon(UserInfoMessage.getUserIcon());
                messageBeanList.add(messageBean);
                chartListAdapter.refreshData(messageBeanList);
                listView.setSelection(chartListAdapter.getCount() - 1);
                editText.setText("");
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                // 消息发送失败的回调
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        toUserBean = (UserBean) intent.getSerializableExtra(INTENT_KEY_USER_BEAN);
        topBarView.setTopBarTitle(toUserBean.getName());
        chartListAdapter = new ChartListAdapter(context, messageBeanList);
        listView.setAdapter(chartListAdapter);
    }

}
