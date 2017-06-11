package com.control.marketing.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.control.marketing.R;
import com.control.marketing.common.BaseActivity;
import com.control.marketing.widget.TopBarView;

public class BossScoreActivity extends BaseActivity {

    private Context context;
    private TopBarView topBarView;

    public static void start(Context context){
        Intent intent = new Intent(context, BossScoreActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss_score);
        context = this;
        initView();
    }

    private void initView() {
        topBarView = (TopBarView) findViewById(R.id.boss_score_top_bar);
        topBarView.setTopBarLeftVisibility(true);
        topBarView.setTopBarTitle("进度审查");
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
