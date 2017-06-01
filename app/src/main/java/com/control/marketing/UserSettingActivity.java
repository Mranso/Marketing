package com.control.marketing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.control.marketing.common.BaseActivity;
import com.control.marketing.widget.TopBarView;

public class UserSettingActivity extends BaseActivity {

    private Context context;
    private TopBarView topBarView;

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
    }

    private void initView() {
        topBarView = (TopBarView) findViewById(R.id.user_setting_top_bar);
        topBarView.setTopBarLeftVisibility(true);
        topBarView.setTopBarTitle("个人设置");
        topBarView.setOnTopBarClickListener(new TopBarView.TopBarClickListener() {
            @Override
            public void leftClickListener() {
                finish();
            }

            @Override
            public void rightClickListener() {

            }
        });
    }
}
