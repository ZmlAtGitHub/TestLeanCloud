package com.test.testleancloud;

import android.content.Context;

import cn.leancloud.AVLogger;
import cn.leancloud.AVOSCloud;
import cn.leancloud.AVObject;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMClientOpenOption;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import cn.leancloud.push.PushService;

public class LeanCloudManager {
    private static final String TAG = LeanCloudManager.class.getSimpleName();

    private static class Singleton {
        public static LeanCloudManager instance = new LeanCloudManager();
    }

    private LeanCloudManager() {
    }

    public static LeanCloudManager getInstance() {
        return Singleton.instance;
    }

    /**
     * init leancloud
     *
     * @param context
     */
    public void init(Context context) {
        String appId = Constant.LC_APPID;
        String appkey = Constant.LC_APPKEY;
        String apiHost = Constant.LC_API_HOST;
        AVOSCloud.initializeSecurely(context, appId, apiHost);

        AVOSCloud.setLogLevel(AVLogger.Level.DEBUG);

        /* LeanCloud Client monitor、Message monitor、Offline Message */
        AVIMClient.setClientEventHandler(new IMClientEventHandler());

        PushService.setAutoWakeUp(true);
        PushService.setDefaultChannelId(context, "0");


        /*test link leancloud*/
//        testLinkLeanCloud();

        /*check to reopen to reconnect client*/
        if (AppUtils.isUserLogin()) {
            ShowLog.show(TAG,"[LeanCloudManager]user has login and reopen to reconnet client");
            openClient(AppUtils.getUserId(), true, new AVIMClientCallback() {
                @Override
                public void done(AVIMClient client, AVIMException e) {
                    if (e == null) {
                        ShowLog.show(TAG,"[LeanCloudManager]reopen to reconnect client successfully");
                    } else {
                        ShowLog.show(TAG,"[LeanCloudManager]reopen to reconnect client failed,error code:" + e.getAppCode() + ",error message:" + e.getMessage());
                    }
                }
            });
        } else {
            ShowLog.show(TAG,"[LeanCloudManager]user has not login,please check in MainActivity whether you has clicked login button");
        }

    }

    /**
     * test link leancloud
     */
    private void testLinkLeanCloud() {
        AVObject testObject = new AVObject("TestObject");
        testObject.put("words", "Hello world!");
        testObject.saveInBackground().subscribe(success -> {
            ShowLog.show(TAG,"[LeanCloudManager]Successfully saved in background");
        }, error -> {
            ShowLog.show(TAG,"[LeanCloudManager]Failed to saved in background: " + error.getMessage());
        });
    }


    /**
     * open client
     *
     * @param clientId  clientId
     * @param reconnect reconnect
     * @param callback  callback
     */
    public void openClient(String clientId, boolean reconnect, AVIMClientCallback callback) {
        AVIMClientOpenOption openOption = new AVIMClientOpenOption();
        openOption.setReconnect(reconnect);
        AVIMClient imClient = AVIMClient.getInstance(clientId, "Mobile");
        imClient.open(openOption, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException excetion) {
                if (excetion == null) {
                    ShowLog.show(TAG,"[LeanCloudManager]open client successfully");
                } else {
                    ShowLog.show(TAG,"[LeanCloudManager]open client failed" + excetion.getMessage());
                }

                if (callback != null) {
                    callback.done(avimClient, excetion);
                }
            }
        });
    }


    /**
     * close leancloud
     *
     * @param clientId
     * @param callback
     */
    public void closeClient(String clientId, AVIMClientCallback callback) {
        AVIMClient imClient = AVIMClient.getInstance(clientId, "Mobile");
        imClient.close(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (e == null) { /*断开连接成功*/
                    ShowLog.show(TAG,"[LeanCloudManager]close leancloud client successfully");
                } else {/*断开连接失败*/
                    ShowLog.show(TAG,"[LeanCloudManager]closeLeanCloudClient with exception whose errorCode：" + e.getCode() + ",errorAppCode:" + e.getAppCode() + ",errorInfo：" + e.getMessage());
                }

                if (callback != null) {
                    callback.done(avimClient, e);
                }
            }
        });
    }

}
