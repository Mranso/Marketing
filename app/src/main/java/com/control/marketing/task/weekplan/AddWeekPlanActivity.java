package com.control.marketing.task.weekplan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.control.marketing.R;
import com.control.marketing.common.BaseActivity;
import com.control.marketing.model.TaskBean;
import com.control.marketing.model.WeekPlanBean;
import com.control.marketing.widget.TopBarView;

import java.util.ArrayList;
import java.util.List;

public class AddWeekPlanActivity extends BaseActivity {

    public static final int REQUEST_CODE = 200;
    public static final String INTENT_KEY_NEW_TASK = "INTENT_KEY_NEW_TASK";

    private TopBarView topBarView;
    private Button selectedTimeButton, submitButton;
    private EditText taskTitleView, taskContentView;
    private EditText weekPlanOneTitleView, weekPlanTwoTitleView, weekPlanThreeTitleView, weekPlanFourTitleView, weekPlanFiveTitleView;

    private DatePicker datePicker;
    private Button getTime;
    private AlertDialog timeSelectDialog;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_week_plan);
        context = this;
        initView();
        initTimeSelectView();
        initListener();
    }

    private void initTimeSelectView() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setCancelable(false);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.add_task_time_select_layout, null);
        datePicker = (DatePicker) dialogView.findViewById(R.id.add_task_time_select_view);
        getTime = (Button) dialogView.findViewById(R.id.add_task_time_select_confirm);
        dialogBuilder.setView(dialogView);
        timeSelectDialog = dialogBuilder.create();
    }

    private void initListener() {
        selectedTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSelectDialog.show();
            }
        });

        getTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();
                selectedTimeButton.setText(year + " - " + month + " - " + day);
                timeSelectDialog.dismiss();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = selectedTimeButton.getText().toString();
                String title = taskTitleView.getText().toString();
                String content = taskContentView.getText().toString();

                if ("选择时间".equals(time) || time.isEmpty()) {
                    Toast.makeText(context, "请选择时间", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (title.isEmpty()) {
                    Toast.makeText(context, "请输入标题", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (content.isEmpty()) {
                    Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show();
                    return;
                }

                WeekPlanBean weekPlanBean = new WeekPlanBean();
                weekPlanBean.setWeekTime(time);
                weekPlanBean.setWeekTitle(title);
                weekPlanBean.setWeekContent(content);
                weekPlanBean.setFinish(false);

                List<TaskBean> taskBeanList = new ArrayList<>();
                TaskBean taskBean1 = new TaskBean();
                taskBean1.setTime(time);
                taskBean1.setTitle(weekPlanOneTitleView.getText().toString());
                taskBean1.setFinish(false);
                taskBeanList.add(taskBean1);

                TaskBean taskBean2 = new TaskBean();
                taskBean2.setTime(time);
                taskBean2.setTitle(weekPlanTwoTitleView.getText().toString());
                taskBean2.setFinish(false);
                taskBeanList.add(taskBean2);

                TaskBean taskBean3 = new TaskBean();
                taskBean3.setTime(time);
                taskBean3.setTitle(weekPlanThreeTitleView.getText().toString());
                taskBean3.setFinish(false);
                taskBeanList.add(taskBean3);

                TaskBean taskBean4 = new TaskBean();
                taskBean4.setTime(time);
                taskBean4.setTitle(weekPlanFourTitleView.getText().toString());
                taskBean4.setFinish(false);
                taskBeanList.add(taskBean4);

                TaskBean taskBean5 = new TaskBean();
                taskBean5.setTime(time);
                taskBean5.setTitle(weekPlanFiveTitleView.getText().toString());
                taskBean5.setFinish(false);
                taskBeanList.add(taskBean5);

                weekPlanBean.setWeekPlans(taskBeanList);

                Intent intent = new Intent();
                intent.putExtra(INTENT_KEY_NEW_TASK, weekPlanBean);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void initView() {
        selectedTimeButton = (Button) findViewById(R.id.add_week_plan_select_time);
        submitButton = (Button) findViewById(R.id.add_week_plan_submit);
        taskTitleView = (EditText) findViewById(R.id.add_week_plan_title);
        taskContentView = (EditText) findViewById(R.id.add_week_plan_content);
        weekPlanOneTitleView = (EditText) findViewById(R.id.add_week_plan_one_title);
        weekPlanTwoTitleView = (EditText) findViewById(R.id.add_week_plan_two_title);
        weekPlanThreeTitleView = (EditText) findViewById(R.id.add_week_plan_three_title);
        weekPlanFourTitleView = (EditText) findViewById(R.id.add_week_plan_four_title);
        weekPlanFiveTitleView = (EditText) findViewById(R.id.add_week_plan_five_title);
        topBarView = (TopBarView) findViewById(R.id.add_week_plan_top_bar);
        initTopBar();
    }

    private void initTopBar() {
        topBarView.setTopBarTitle("新建任务");
        topBarView.setTopBarLeftVisibility(true);
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
