package com.test.testleancloud;

import android.util.Log;

/**
 * Created by Administrator on 2018/5/29.
 * 打印日志
 */

public class ShowLog {
    private static String TAG = "ShowLog";


    /**
     * 打印日志
     *
     * @param msg
     */
    public static void show(String msg) {
        show(TAG, msg);
    }

    /**
     * 打印日志
     *
     * @param TAG
     * @param msg
     */
    public static void show(String TAG, String msg) {
        Log.i(TAG, msg);
    }

    public static void showLC(String msg) {
        Log.i("LeanCloud", msg);
    }

    /**
     * 打印报错信息
     *
     * @param TAG
     * @param msg
     */
    public static void showWarn(String TAG, String msg) {
        android.util.Log.w(TAG, msg);
    }

    /**
     * 打印调试信息
     *
     * @param TAG
     * @param msg
     */
    public static void showDebug(String TAG, String msg) {
        android.util.Log.d(TAG, msg);
    }

    /**
     * 打印报错信息
     *
     * @param TAG
     * @param msg
     */
    public static void showError(String TAG, String msg) {
        android.util.Log.e(TAG, msg);
    }

    /**
     * 打印报错信息
     *
     * @param TAG
     * @param msg
     * @param throwable
     */
    public static void showError(String TAG, String msg, Throwable throwable) {
        android.util.Log.e(TAG, msg, throwable);
    }
}
