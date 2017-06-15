package com.control.marketing.task;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.control.marketing.R;
import com.control.marketing.common.BaseActivity;
import com.control.marketing.model.TaskBean;
import com.control.marketing.widget.FullGridView;
import com.control.marketing.widget.TopBarView;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class AddTaskActivity extends BaseActivity {

    public static final int REQUEST_IMAGE = 100;
    public static final int REQUEST_CODE = 200;
    public static final String INTENT_KEY_NEW_TASK = "INTENT_KEY_NEW_TASK";

    private TopBarView topBarView;
    private Button selectedTimeButton, submitButton;
    private EditText taskTitleView, taskContentView;
    private FullGridView imageGridView;

    private DatePicker datePicker;
    private Button getTime;
    private AlertDialog timeSelectDialog;
    private Context context;
    private AddTaskImageGridViewAdapter addTaskImageGridViewAdapter;
    private List<String> imageUrls = new ArrayList<>();

    public static void startForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, AddTaskActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
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

        imageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (AddTaskImageGridViewAdapter.DEFAULT_IMAGE_URL.equals(imageUrls.get(i))) {
                    int count = 9 - addTaskImageGridViewAdapter.getCount() + 1;
                    MultiImageSelector.create(context)
                            .showCamera(true)
                            .count(count)
                            .multi()
                            .start(AddTaskActivity.this, REQUEST_IMAGE);
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = selectedTimeButton.getText().toString();
                String title = taskTitleView.getText().toString();
                String content = taskContentView.getText().toString();
                imageUrls.remove(AddTaskImageGridViewAdapter.DEFAULT_IMAGE_URL);

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

                TaskBean taskBean = new TaskBean();
                taskBean.setTime(time);
                taskBean.setTitle(title);
                taskBean.setContent(content);
                taskBean.setImages(imageUrls);
                taskBean.setFinish(false);

                Intent intent = new Intent();
                intent.putExtra(INTENT_KEY_NEW_TASK, taskBean);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                List<String> imagePaths = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                if (imagePaths.size() >= 9) {
                    imageUrls.clear();
                    imageUrls.addAll(imagePaths);
                } else {
                    if ((imagePaths.size() + imageUrls.size() - 1) >= 9) {
                        imageUrls.remove(imageUrls.size() - 1);
                        imageUrls.addAll(imagePaths);
                    } else {
                        imageUrls.addAll(imageUrls.size() - 1, imagePaths);
                    }
                }
                addTaskImageGridViewAdapter.refreshData(imageUrls);
            }
        }
    }

    private void initView() {
        selectedTimeButton = (Button) findViewById(R.id.add_task_select_time);
        submitButton = (Button) findViewById(R.id.add_task_submit);
        taskTitleView = (EditText) findViewById(R.id.add_task_title);
        taskContentView = (EditText) findViewById(R.id.add_task_content);
        imageGridView = (FullGridView) findViewById(R.id.add_task_image_grid_view);
        topBarView = (TopBarView) findViewById(R.id.add_task_top_bar);
        initTopBar();
        initImageGridView();
    }

    private void initImageGridView() {
        addTaskImageGridViewAdapter = new AddTaskImageGridViewAdapter(context);
        imageGridView.setAdapter(addTaskImageGridViewAdapter);

        if (imageUrls.size() <= 0) {
            imageUrls.add(AddTaskImageGridViewAdapter.DEFAULT_IMAGE_URL);
            addTaskImageGridViewAdapter.refreshData(imageUrls);
        }
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
