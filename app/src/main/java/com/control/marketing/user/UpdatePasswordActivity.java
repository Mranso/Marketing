package com.control.marketing.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.control.marketing.R;
import com.control.marketing.common.BaseActivity;
import com.control.marketing.widget.TopBarView;

public class UpdatePasswordActivity extends BaseActivity {

    private Context context;
    private TopBarView topBarView;
    private EditText oldPassword, newOnePassword, newTwoPassword;
    private Button submit;

    public static void start(Context context){
        Intent intent = new Intent(context, UpdatePasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        context = this;
        initView();
        initListener();
    }

    private void initListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (oldPassword.getText().toString().isEmpty()) {
                    Toast.makeText(context, "请输入旧密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                String newPasswordOne = newOnePassword.getText().toString();
                String newPasswordTwo = newTwoPassword.getText().toString();
                if (newPasswordOne.equals(newPasswordTwo)) {
                    updatePassword();
                } else {
                    Toast.makeText(context, "请输入相同的新密码", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private void updatePassword() {

    }

    private void initView() {
        oldPassword = (EditText) findViewById(R.id.update_password_old);
        newOnePassword = (EditText) findViewById(R.id.update_password_new_one);
        newTwoPassword = (EditText) findViewById(R.id.update_password_new_two);
        submit = (Button) findViewById(R.id.update_password_submit);
        topBarView = (TopBarView) findViewById(R.id.update_password_top_bar);
        topBarView.setTopBarTitle("修改密码");
        topBarView.setTopBarLeftVisibility(true);
    }
}
