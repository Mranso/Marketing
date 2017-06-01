package com.control.marketing.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.marketing.R;
import com.control.marketing.model.MessageBean;
import com.control.marketing.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: yuantian
 * date: 2017/5/16
 * version: 2.0.0
 */

public class ChartListAdapter extends BaseAdapter {

    private Context context;
    private List<MessageBean> messageBeanList;

    public ChartListAdapter(Context context, List<MessageBean> messageBeanList) {
        this.context = context;
        this.messageBeanList = messageBeanList;
    }

    public void refreshData(List<MessageBean> messageBeanList){
        this.messageBeanList = messageBeanList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messageBeanList.size();
    }

    @Override
    public MessageBean getItem(int i) {
        return messageBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return messageBeanList.get(position).getMessageType();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ToUserTextViewHolder toUserTextViewHolder;
        FromUserTextViewHolder fromUserTextViewHolder;
        MessageBean messageBean = messageBeanList.get(i);
        if (view == null) {
            switch (messageBean.getMessageType()) {
                case 1:
                    view = LayoutInflater.from(context).inflate(R.layout.chart_list_item_from_user_text_type_layout, null);
                    fromUserTextViewHolder = new FromUserTextViewHolder();
                    fromUserTextViewHolder.messageTime = (TextView) view.findViewById(R.id.chart_message_from_user_text_time);
                    fromUserTextViewHolder.messageContext = (TextView) view.findViewById(R.id.chart_message_from_user_text_content);
                    fromUserTextViewHolder.userIcon = (ImageView) view.findViewById(R.id.chart_message_from_user_icon);

                    fromUserTextViewHolder.messageTime.setText(messageBean.getMessageTime());
                    fromUserTextViewHolder.messageContext.setText(messageBean.getMessageContent());
                    ImageUtils.setHeadImage(context, messageBean.getUserIcon(), fromUserTextViewHolder.userIcon);

                    view.setTag(fromUserTextViewHolder);
                    break;
                case 2:
                    view = LayoutInflater.from(context).inflate(R.layout.chart_list_item_to_user_text_type_layout, null);
                    toUserTextViewHolder = new ToUserTextViewHolder();
                    toUserTextViewHolder.messageTime = (TextView) view.findViewById(R.id.chart_message_to_user_text_time);
                    toUserTextViewHolder.messageContext = (TextView) view.findViewById(R.id.chart_message_to_user_text_content);
                    toUserTextViewHolder.userIcon = (ImageView) view.findViewById(R.id.chart_message_to_user_icon);

                    toUserTextViewHolder.messageTime.setText(messageBean.getMessageTime());
                    toUserTextViewHolder.messageContext.setText(messageBean.getMessageContent());
                    ImageUtils.setHeadImage(context, messageBean.getUserIcon(), toUserTextViewHolder.userIcon);

                    view.setTag(toUserTextViewHolder);
                    break;
            }
        } else {
            switch (messageBean.getMessageType()) {
                case 1:
                    fromUserTextViewHolder = (FromUserTextViewHolder) view.getTag();
                    fromUserTextViewHolder.messageTime.setText(messageBean.getMessageTime());
                    fromUserTextViewHolder.messageContext.setText(messageBean.getMessageContent());
                    ImageUtils.setHeadImage(context, messageBean.getUserIcon(), fromUserTextViewHolder.userIcon);
                    break;
                case 2:
                    toUserTextViewHolder = (ToUserTextViewHolder) view.getTag();
                    toUserTextViewHolder.messageTime.setText(messageBean.getMessageTime());
                    toUserTextViewHolder.messageContext.setText(messageBean.getMessageContent());
                    ImageUtils.setHeadImage(context, messageBean.getUserIcon(), toUserTextViewHolder.userIcon);
                    break;
            }
        }
        return view;
    }

    private class ToUserTextViewHolder {
        private TextView messageTime;
        private TextView messageContext;
        private ImageView userIcon;
    }

    private class FromUserTextViewHolder {
        private TextView messageTime;
        private TextView messageContext;
        private ImageView userIcon;
    }
}
