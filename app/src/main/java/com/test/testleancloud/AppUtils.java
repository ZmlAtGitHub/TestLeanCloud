package com.test.testleancloud;

import android.app.ActivityManager;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/11/3.
 */

public class AppUtils {
    private static String TAG = "AppUtils";

    private static final String KEY_USERID = "UserId";

    private static String CONFIG = "testlc.sp";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    static {
        sp = TestLeanCloudApp.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public static void saveUserId(String userId) {
        if (TextUtils.isEmpty(userId))
            throw new NullPointerException("AppUtils.saveUserId() variable of userId cannot be null or length == 0,please check it!");

        saveString(KEY_USERID, userId);
    }

    public static String getUserId() {
        return getString(KEY_USERID);
    }

    public static boolean isUserLogin() {
        return !TextUtils.isEmpty(getString(KEY_USERID));
    }

    private static void saveString(String key, String data) {
        editor.putString(key, data).apply();
    }

    private static String getString(String key) {
        return getString(key, "");
    }

    private static String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    /**
     * 获取进程名。
     * 由于app是一个多进程应用，因此每个进程被os创建时，
     * onCreate()方法均会被执行一次，
     * 进行辨别初始化，针对特定进程进行相应初始化工作，
     * 此方法可以提高一半启动时间。
     *
     * @param context 上下文环境对象
     * @return 获取此进程的进程名
     */
    public static boolean checkProgressInit(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return false;
        }
        String mainProgressName = context.getPackageName();
        int myPid = android.os.Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
            Log.i(TAG, "running progress:" + runningAppProcess.processName);
            if (runningAppProcess.pid == myPid
                    && !TextUtils.isEmpty(runningAppProcess.processName)
                    && runningAppProcess.processName.equals(mainProgressName)) {
//                return runningAppProcess.processName;
                return true;
            }
        }
        return false;
    }


    /**
     * 获取进程号对应的进程名
     *
     * @return 进程名
     */
    public static String getProcessName() {
        int pid = android.os.Process.myPid();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
