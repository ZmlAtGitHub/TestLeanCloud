package com.test.testleancloud;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import cn.leancloud.AVLogger;
import cn.leancloud.AVOSCloud;
import cn.leancloud.AVObject;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.push.PushService;

public class TestLeanCloudApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        if (AppUtils.checkProgressInit(this)) {
            ShowLog.showLC("start to init leancloud in application");

            LeanCloudManager.getInstance().init(this);
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }

    public static Context getContext() {
        return mContext;
    }

}
