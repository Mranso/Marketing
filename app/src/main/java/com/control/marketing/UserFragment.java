package com.control.marketing;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.control.marketing.common.BaseFragment;
import com.control.marketing.widget.TopBarView;

public class UserFragment extends BaseFragment {

    private Context context;
    private TopBarView topBarView;
    private LinearLayout userInfoView, bossScoreView, userSettingView;

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
        initListener();
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
    }

    private void initView(View view) {
        userInfoView = (LinearLayout) view.findViewById(R.id.user_fragment_user_info);
        bossScoreView = (LinearLayout) view.findViewById(R.id.user_fragment_boss_score);
        userSettingView = (LinearLayout) view.findViewById(R.id.user_fragment_user_setting);
        topBarView = (TopBarView) view.findViewById(R.id.user_fragment_top_bar);
        topBarView.setTopBarTitle("我的");
    }
}
