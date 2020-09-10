package com.test.testleancloud;

import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMClientEventHandler;
import cn.leancloud.im.v2.AVIMClientOpenOption;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import cn.leancloud.im.v2.callback.AVIMClientStatusCallback;

/**
 * Updated by Administrator on 2018/11/16
 * <p>
 * LeanCloud Client管理
 * https://leancloud.cn/docs/realtime-guide-intermediate.html#hash-2140562613
 * </p>
 */
public class IMClientEventHandler extends AVIMClientEventHandler {
    private static String TAG = IMClientEventHandler.class.getSimpleName();

    private static final int ERRORCODE_REMOTE_LOGIN = 4111;

    public IMClientEventHandler() {
    }

    @Override
    public void onConnectionPaused(AVIMClient avimClient) {
        ShowLog.show(TAG, "聊天连接==>onConnectionPaused");
    }

    @Override
    public void onConnectionResume(AVIMClient avimClient) {
        ShowLog.show(TAG, "聊天连接==>onConnectionResume");
    }

    @Override
    public void onClientOffline(final AVIMClient avimClient, int errorCode) {
        ShowLog.showLC("聊天连接==>onClientOffline=>errorCode：" + errorCode);

        if (errorCode == ERRORCODE_REMOTE_LOGIN) {
            ShowLog.showLC("onClientOffline==>account login on another device");

            avimClient.close(new AVIMClientCallback() {
                @Override
                public void done(AVIMClient client, AVIMException e) {
                    if (e == null) {
                        ShowLog.show(TAG, "close client successfully");
                    } else {
                        ShowLog.show(TAG, "close client failed" + e.getMessage());
                    }
                }
            });
        }

    }

}
