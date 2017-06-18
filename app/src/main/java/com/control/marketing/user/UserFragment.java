package com.control.marketing.user;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.control.marketing.R;
import com.control.marketing.common.BaseFragment;
import com.control.marketing.utils.ImageUtils;
import com.control.marketing.widget.TopBarView;

import org.w3c.dom.Text;

public class UserFragment extends BaseFragment {

    private Context context;
    private ImageView userIconView;
    private TextView userNameView;
    private TopBarView topBarView;
    private LinearLayout userInfoView, bossScoreView, userSettingView, userPlanTopView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getActivity();
        initView(view);
        initData();
        initListener();
    }

    private void initData() {
        ImageUtils.setHeadImage(context, "http://b.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=f0c5c08030d3d539c16807c70fb7c566/8ad4b31c8701a18bbef9f231982f07082838feba.jpg", userIconView);
        userNameView.setText("朱俊铭");
    }

    private void initListener() {
        userInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfoActivity.start(context);
            }
        });

        bossScoreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BossScoreActivity.start(context);
            }
        });

        userSettingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSettingActivity.start(context);
            }
        });

        userPlanTopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlanTopActivity.start(context);
            }
        });
    }

    private void initView(View view) {
        userIconView = (ImageView) view.findViewById(R.id.user_fragment_user_icon);
        userNameView = (TextView) view.findViewById(R.id.user_fragment_user_name);
        userInfoView = (LinearLayout) view.findViewById(R.id.user_fragment_user_info);
        bossScoreView = (LinearLayout) view.findViewById(R.id.user_fragment_boss_score);
        userSettingView = (LinearLayout) view.findViewById(R.id.user_fragment_user_setting);
        userPlanTopView = (LinearLayout) view.findViewById(R.id.user_fragment_my_top);
        topBarView = (TopBarView) view.findViewById(R.id.user_fragment_top_bar);
        topBarView.setTopBarTitle("我的");
    }
}
