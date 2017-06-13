package com.control.marketing.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.control.marketing.R;
import com.control.marketing.common.BaseActivity;
import com.control.marketing.widget.TopBarView;

public class AppInfoActivity extends BaseActivity {

    private Context context;
    private TopBarView topBarView;
    private Button updateVersionView;

    public static void start(Context context){
        Intent intent = new Intent(context, AppInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
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

        updateVersionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "当前是最新版本", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        updateVersionView = (Button) findViewById(R.id.app_info_update_version);
        topBarView = (TopBarView) findViewById(R.id.app_info_top_bar);
        topBarView.setTopBarTitle("版本信息");
        topBarView.setTopBarLeftVisibility(true);
    }
}
