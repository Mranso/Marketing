package com.control.marketing.maintab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.control.marketing.message.MessageFragment;
import com.control.marketing.R;
import com.control.marketing.task.TaskFragment;
import com.control.marketing.UserFragment;
import com.control.marketing.common.BaseActivity;
import com.control.marketing.widget.NoScrollViewPager;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private static final int VIEW_PAGE_FLAG_ONE = 0;
    private static final int VIEW_PAGE_FLAG_TWO = 1;
    private static final int VIEW_PAGE_FLAG_THREE = 2;

    private RadioButton firstButton, secondButton, thirdButton;
    private NoScrollViewPager viewPager;
    private RadioGroup radioGroup;
    private Context context;
    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        initFragment();
        initListener();
        switchViewPage(VIEW_PAGE_FLAG_ONE);
    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_tab_radio_first:
                        switchViewPage(VIEW_PAGE_FLAG_ONE);
                        break;
                    case R.id.main_tab_radio_second:
                        switchViewPage(VIEW_PAGE_FLAG_TWO);
                        break;
                    case R.id.main_tab_radio_third:
                        switchViewPage(VIEW_PAGE_FLAG_THREE);
                        break;
                }
            }
        });
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new MessageFragment());
        fragmentArrayList.add(new TaskFragment());
        fragmentArrayList.add(new UserFragment());
        FragmentPageViewAdapter fragmentPageViewAdapter = new FragmentPageViewAdapter(fragmentManager, fragmentArrayList);
        viewPager.setOffscreenPageLimit(fragmentArrayList.size() - 1);
        viewPager.setAdapter(fragmentPageViewAdapter);
    }

    private void initView() {
        viewPager = (NoScrollViewPager) findViewById(R.id.main_tab_view_page);
        firstButton = (RadioButton) findViewById(R.id.main_tab_radio_first);
        secondButton = (RadioButton) findViewById(R.id.main_tab_radio_second);
        thirdButton = (RadioButton) findViewById(R.id.main_tab_radio_third);
        radioGroup = (RadioGroup) findViewById(R.id.main_tab_radio_group);


        Drawable firstButtonDrawable = getResources().getDrawable(R.drawable.tab_everyday_icon_bg);
        firstButtonDrawable.setBounds(0, 0, 80, 80);
        firstButton.setCompoundDrawables(null, firstButtonDrawable, null, null);
        Drawable secondButtonDrawable = getResources().getDrawable(R.drawable.tab_beauty_icon_bg);
        secondButtonDrawable.setBounds(0, 0, 80, 80);
        secondButton.setCompoundDrawables(null, secondButtonDrawable, null, null);
        Drawable thirdButtonDrawable = getResources().getDrawable(R.drawable.tab_personal_icon_bg);
        thirdButtonDrawable.setBounds(0, 0, 80, 80);
        thirdButton.setCompoundDrawables(null, thirdButtonDrawable, null, null);
    }

    private void switchViewPage(int flag) {
        viewPager.setCurrentItem(flag, false);
        switch (flag) {
            case VIEW_PAGE_FLAG_ONE:
                firstButton.setChecked(true);
                break;
            case VIEW_PAGE_FLAG_TWO:
                secondButton.setChecked(true);
                break;
            case VIEW_PAGE_FLAG_THREE:
                thirdButton.setChecked(true);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Toast.makeText(context, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }
}
