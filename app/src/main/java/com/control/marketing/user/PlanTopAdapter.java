package com.control.marketing.user;

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
 * date: 2017/5/21
 * version: 2.0.0
 */

public class PlanTopAdapter extends BaseAdapter {

    private Context context;
    private List<UserBean> userBeanArrayList = new ArrayList<>();

    public PlanTopAdapter(Context context) {
        this.context = context;
    }

    public void refreshData(List<UserBean> taskBeanList) {
        this.userBeanArrayList = taskBeanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return userBeanArrayList.size();
    }

    @Override
    public UserBean getItem(int i) {
        return userBeanArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PlanTopItemViewHolder planTopItemViewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.task_top_list_item_layout, viewGroup, false);
            planTopItemViewHolder = new PlanTopItemViewHolder();
            planTopItemViewHolder.userRankView = (TextView) view.findViewById(R.id.plan_top_user_rank);
            planTopItemViewHolder.userIconView = (ImageView) view.findViewById(R.id.plan_top_user_icon);
            planTopItemViewHolder.userNameView = (TextView) view.findViewById(R.id.plan_top_user_name);
            planTopItemViewHolder.userScoreView = (TextView) view.findViewById(R.id.plan_top_user_score);
            view.setTag(planTopItemViewHolder);
        } else {
            planTopItemViewHolder = (PlanTopItemViewHolder) view.getTag();
        }

        UserBean userBean = userBeanArrayList.get(i);
        planTopItemViewHolder.userRankView.setText(String.valueOf(i + 1));
        ImageUtils.setHeadImage(context, userBean.getHeaderIcon(), planTopItemViewHolder.userIconView);
        planTopItemViewHolder.userNameView.setText(userBean.getName());
        planTopItemViewHolder.userScoreView.setText("9898");
        return view;
    }

    private class PlanTopItemViewHolder {
        private TextView userRankView;
        private ImageView userIconView;
        private TextView userNameView;
        private TextView userScoreView;
    }
}
