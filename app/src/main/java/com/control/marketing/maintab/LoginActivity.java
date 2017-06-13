package com.control.marketing.maintab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.control.marketing.R;
import com.control.marketing.common.BaseActivity;
import com.control.marketing.utils.SharedPreferencesUtils;
import com.control.marketing.utils.StatusBarUtils;

public class LoginActivity extends BaseActivity {

    private EditText accountView, passwordView;
    private CheckBox rememberPasswordView;
    private Button loginView;
    private Context context;

    public static void start(Context context){
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
                SharedPreferencesUtils.getInstance().putBoolean(SharedPreferencesUtils.SP_LOGIN_REMEMBER_PASSWORD_AND_ACCOUNT, rememberPasswordView.isChecked());
                if (rememberPasswordView.isChecked()) {
                    SharedPreferencesUtils.getInstance().putString(SharedPreferencesUtils.SP_LOGIN_ACCOUNT, accountView.getText().toString());
                    SharedPreferencesUtils.getInstance().putString(SharedPreferencesUtils.SP_LOGIN_PASSWORD, passwordView.getText().toString());
                }
                MainActivity.start(context);
                finish();
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
}
