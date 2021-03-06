package com.control.marketing.common;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.control.marketing.message.McReceiveMessageListener;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class McApplication extends Application {

    private static McApplication application;

    public McApplication() {
        application = this;
    }

    public static synchronized McApplication getInstance() {
        if (application == null) {
            application = new McApplication();
        }
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIMClient 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {
            RongIM.init(this);
            RongIMClient.setOnReceiveMessageListener(new McReceiveMessageListener());
        }
    }

    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}