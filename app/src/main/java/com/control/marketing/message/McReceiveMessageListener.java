package com.control.marketing.message;

import com.control.marketing.model.MessageBean;
import com.control.marketing.utils.TimeUtils;

import org.greenrobot.eventbus.EventBus;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

/**
 * description:
 * author: yuantian
 * date: 2017/7/5
 * version: 2.0.0
 */

public class McReceiveMessageListener implements RongIMClient.OnReceiveMessageListener {
    @Override
    public boolean onReceived(Message message, int i) {
        TextMessage messageContent = (TextMessage) message.getContent();
        MessageBean messageBean = new MessageBean();
        messageBean.setFromUser(message.getSenderUserId());
        messageBean.setMessageContent(messageContent.getContent());
        messageBean.setMessageType(1);
        messageBean.setMessageTime(TimeUtils.getDateString(message.getSentTime()));
        EventBus.getDefault().post(messageBean);
        return true;
    }
}
