package com.control.marketing.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.control.marketing.R;
import com.control.marketing.common.BaseActivity;
import com.control.marketing.widget.TopBarView;

public class UserInfoActivity extends BaseActivity {

    private TopBarView topBarView;
    private EditText userNameView, userMobileView, userEmailView;
    private LinearLayout userIconLayout;
    private ImageView userIconView;
    private Button submit;

    public static void start(Context context){
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
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

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        userIconLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userNameView.getText().toString();
                String mobile = userMobileView.getText().toString();
                String email = userEmailView.getText().toString();
            }
        });
    }

    private void initView() {
        userIconLayout = (LinearLayout) findViewById(R.id.user_info_icon_layout);
        userIconView = (ImageView) findViewById(R.id.user_info_icon);
        userNameView = (EditText) findViewById(R.id.user_info_name);
        userMobileView = (EditText) findViewById(R.id.user_info_mobile);
        userEmailView = (EditText) findViewById(R.id.user_info_email);
        submit = (Button) findViewById(R.id.user_info_update_info);
        topBarView = (TopBarView) findViewById(R.id.user_info_top_bar);
        topBarView.setTopBarLeftVisibility(true);
        topBarView.setTopBarTitle("个人资料");
    }
}
