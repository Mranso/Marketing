package com.control.marketing.maintab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.control.marketing.R;
import com.control.marketing.common.BaseActivity;
import com.control.marketing.common.McApplication;
import com.control.marketing.model.UserBean;
import com.control.marketing.model.UserInfoMessage;
import com.control.marketing.utils.SharedPreferencesUtils;
import com.control.marketing.utils.StatusBarUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class LoginActivity extends BaseActivity {

    private EditText accountView, passwordView;
    private CheckBox rememberPasswordView;
    private Button loginView;
    private Context context;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        StatusBarUtils.setColor(this, Color.parseColor("#1C86EE"));
        initView();
        initListener();
    }

    private void initListener() {
        loginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountView.getText().toString();
                String password = passwordView.getText().toString();
                if (account.isEmpty() || password.isEmpty()) {
                    Toast.makeText(context, "请输入账号或密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                initRongCloud(account);
                SharedPreferencesUtils.getInstance().putBoolean(SharedPreferencesUtils.SP_LOGIN_REMEMBER_PASSWORD_AND_ACCOUNT, rememberPasswordView.isChecked());
                if (rememberPasswordView.isChecked()) {
                    SharedPreferencesUtils.getInstance().putString(SharedPreferencesUtils.SP_LOGIN_ACCOUNT, account);
                    SharedPreferencesUtils.getInstance().putString(SharedPreferencesUtils.SP_LOGIN_PASSWORD, password);
                }
            }
        });
    }

    private void initView() {
        accountView = (EditText) findViewById(R.id.login_account);
        passwordView = (EditText) findViewById(R.id.login_password);
        rememberPasswordView = (CheckBox) findViewById(R.id.login_remember_password);
        loginView = (Button) findViewById(R.id.login);

        boolean spCheckboxStatus = SharedPreferencesUtils.getInstance().getBoolean(SharedPreferencesUtils.SP_LOGIN_REMEMBER_PASSWORD_AND_ACCOUNT, false);
        rememberPasswordView.setChecked(spCheckboxStatus);
        String spAccountString = SharedPreferencesUtils.getInstance().getString(SharedPreferencesUtils.SP_LOGIN_ACCOUNT, "");
        String spPasswordString = SharedPreferencesUtils.getInstance().getString(SharedPreferencesUtils.SP_LOGIN_PASSWORD, "");
        if (!spAccountString.isEmpty() && spCheckboxStatus) {
            accountView.setText(spAccountString);
            passwordView.setText(spPasswordString);
        }
    }

    private void initRongCloud(String account) {
        String token = "";
        if ("111111".equals(account)) {
            token = "OMAJy2mFMHzd4nn/H1UtQxSuRgh3bHYa2IvH6oyU0ArfGIsdXPC+hwQILviu/hR2+a1KIJpzkAccX7+dksMLXA==";
        } else if ("222222".equals(account)) {
            token = "+h3D5l6BbVHjCYUTTsI0C4rwFcIqBfflUFxqMr53IWGh6GuHOde6OXjZGuGAAl0mbWHvLIc0hNPPHvGE2l3YcA==";
        }
        connect(token);
    }

    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(McApplication.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String userId) {
                    UserBean userBean = new UserBean();
                    if ("111111".equals(userId)) {
                        userBean.setId(userId);
                        userBean.setName("用户一");
                        userBean.setHeaderIcon("http://b.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=f0c5c08030d3d539c16807c70fb7c566/8ad4b31c8701a18bbef9f231982f07082838feba.jpg");
                    } else {
                        userBean.setId(userId);
                        userBean.setName("用户二");
                        userBean.setHeaderIcon("http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg");
                    }
                    UserInfoMessage.cacheUserInfo(userBean);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }
}
