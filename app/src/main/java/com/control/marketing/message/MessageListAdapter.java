package com.control.marketing.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.control.marketing.R;
import com.control.marketing.model.UserBean;
import com.control.marketing.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: yuantian
 * date: 2017/5/9
 * version: 2.0.0
 */

public class MessageListAdapter extends BaseAdapter {

    private Context context;
    private List<UserBean> userBeanList = new ArrayList<>();

    public MessageListAdapter(Context context) {
        this.context = context;
    }

    public void refreshData(List<UserBean> userBeanList) {
        this.userBeanList = userBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return userBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return userBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MessageItemViewHolder messageItemViewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.message_fragment_list_item_layout, null);
            messageItemViewHolder = new MessageItemViewHolder();
            messageItemViewHolder.userIcon = (ImageView) view.findViewById(R.id.message_fragment_list_item_user_icon);
            messageItemViewHolder.userName = (TextView) view.findViewById(R.id.message_fragment_list_item_user_name);
            messageItemViewHolder.lastMessageTime = (TextView) view.findViewById(R.id.message_fragment_list_item_last_time);
            messageItemViewHolder.lastMessage = (TextView) view.findViewById(R.id.message_fragment_list_item_last_message);
            view.setTag(messageItemViewHolder);
        } else {
            messageItemViewHolder = (MessageItemViewHolder) view.getTag();
        }

        UserBean userBean = userBeanList.get(i);
        ImageUtils.setHeadImage(context, userBean.getHeaderIcon(), messageItemViewHolder.userIcon);
        messageItemViewHolder.userName.setText(userBean.getName());
        messageItemViewHolder.lastMessageTime.setText(userBean.getLsatMessageTime());
        messageItemViewHolder.lastMessage.setText(userBean.getLastMessage());

        return view;
    }

    private class MessageItemViewHolder {
        private ImageView userIcon;
        private TextView userName;
        private TextView lastMessageTime;
        private TextView lastMessage;
    }
}
