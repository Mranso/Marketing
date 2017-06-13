package com.control.marketing.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.control.marketing.R;
import com.control.marketing.common.BaseActivity;
import com.control.marketing.maintab.LoginActivity;
import com.control.marketing.widget.TopBarView;

public class UserSettingActivity extends BaseActivity {

    private Context context;
    private TopBarView topBarView;
    private Button logout;
    private LinearLayout updatePasswordLayout, appInfoLayout;

    public static void start(Context context){
        Intent intent = new Intent(context, UserSettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        context = this;
        initView();
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

        updatePasswordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdatePasswordActivity.start(context);
            }
        });

        appInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppInfoActivity.start(context);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.start(context);
            }
        });
    }

    private void initView() {
        updatePasswordLayout = (LinearLayout) findViewById(R.id.user_setting_account_layout);
        appInfoLayout = (LinearLayout) findViewById(R.id.user_setting_version_info_layout);
        logout = (Button) findViewById(R.id.user_setting_logout_layout);
        topBarView = (TopBarView) findViewById(R.id.user_setting_top_bar);
        topBarView.setTopBarLeftVisibility(true);
        topBarView.setTopBarTitle("个人设置");
    }
}
