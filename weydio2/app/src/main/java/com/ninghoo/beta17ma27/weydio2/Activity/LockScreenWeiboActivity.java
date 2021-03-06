package com.ninghoo.beta17ma27.weydio2.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.ninghoo.beta17ma27.weydio2.Interface.Constants;
import com.ninghoo.beta17ma27.weydio2.R;
import com.sina.weibo.sdk.auth.AuthInfo;

/**
 * Created by ningfu on 17-3-30.
 */

public class LockScreenWeiboActivity extends AppCompatActivity
{
    private AuthInfo authInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // 仅使用WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD的时候，该flag会在解锁后显示activity。
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                |WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        setContentView(R.layout.activity_weibowakelock);

    }
}
