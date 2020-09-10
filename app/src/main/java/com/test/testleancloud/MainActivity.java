package com.test.testleancloud;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String TEST_USER_ABC = "user_abc";

    @BindView(R.id.am_tv_register_user)
    TextView registerUserTV;
    @BindView(R.id.am_tv_reconnect_user)
    TextView reconnectUserTV;
    @BindView(R.id.am_tv_logout_user)
    TextView logoutUserTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.am_tv_register_user, R.id.am_tv_reconnect_user, R.id.am_tv_logout_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.am_tv_register_user:
                registerAndLoginUser(TEST_USER_ABC);
                break;
            case R.id.am_tv_reconnect_user:
                reopen(TEST_USER_ABC);
                break;
            case R.id.am_tv_logout_user:
                logout(TEST_USER_ABC);
                break;
        }
    }

    private void registerAndLoginUser(String clientId) {
        ShowLog.show(TAG, "start to open client==>");
        LeanCloudManager.getInstance().openClient(clientId, false, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient client, AVIMException e) {
                if (e == null) {
                    AppUtils.saveUserId(clientId);

                    ShowLog.show(TAG, "open client successfully");
                } else {
                    AppUtils.saveUserId("");

                    ShowLog.show(TAG, "open client failed" + e.getMessage());
                }
            }
        });
    }

    private void reopen(String clientId) {
        ShowLog.show(TAG, "start to reopen client==>");

        LeanCloudManager.getInstance().openClient(clientId, true, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient client, AVIMException e) {
                if (e == null) {
                    AppUtils.saveUserId(clientId);

                    ShowLog.show(TAG, "reopen client successfully");
                } else {
                    AppUtils.saveUserId("");

                    ShowLog.show(TAG, "reopen client failed" + e.getMessage());
                }
            }
        });
    }

    private void logout(String user_abc) {
        LeanCloudManager.getInstance().closeClient(user_abc, new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (e == null) { /*断开连接成功*/
                    ShowLog.show(TAG, "===>close leancloud client successfully,start to show LoginActivity");
                } else {/*断开连接失败*/
                    ShowLog.show(TAG, "===>closeLeanCloudClient with exception whose errorCode：" + e.getCode() + ",errorAppCode:" + e.getAppCode() + ",errorInfo：" + e.getMessage());
                }
            }
        });
    }

}