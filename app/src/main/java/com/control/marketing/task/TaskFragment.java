package com.control.marketing.task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.control.marketing.R;
import com.control.marketing.common.BaseFragment;
import com.control.marketing.task.everydayplan.EverydayPlanListFragment;
import com.control.marketing.task.monthplan.MonthPlanListFragment;
import com.control.marketing.task.weekplan.WeekPlanListFragment;
import com.control.marketing.widget.PagerSlidingTabStrip;
import com.control.marketing.widget.TopBarView;

import java.util.ArrayList;

public class TaskFragment extends BaseFragment {

    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerSlidingTabStrip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.task_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initFragment();
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.task_fragment_view_pager);
        pagerSlidingTabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.task_fragment_sliding_tab);

        TopBarView topBarView = (TopBarView) view.findViewById(R.id.task_fragment_top_bar);
        topBarView.setTopBarTitle("计划");
    }

    private void initFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new EverydayPlanListFragment());
        fragmentArrayList.add(new WeekPlanListFragment());
        fragmentArrayList.add(new MonthPlanListFragment());
        TaskViewPagerAdapter taskViewPagerAdapter = new TaskViewPagerAdapter(fragmentManager, fragmentArrayList);
        viewPager.setOffscreenPageLimit(fragmentArrayList.size() - 1);
        viewPager.setAdapter(taskViewPagerAdapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
    }
}
