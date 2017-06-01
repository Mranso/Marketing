package com.control.marketing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.control.marketing.common.BaseActivity;
import com.control.marketing.widget.TopBarView;

public class UserInfoActivity extends BaseActivity {

    private TopBarView topBarView;

    public static void start(Context context){
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
    }

    private void initView() {
        topBarView = (TopBarView) findViewById(R.id.user_info_top_bar);
        topBarView.setTopBarLeftVisibility(true);
        topBarView.setTopBarTitle("个人资料");
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
