package com.ninghoo.beta17ma27.weydio2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.ninghoo.beta17ma27.weydio2.R;
import com.ninghoo.beta17ma27.weydio2.WeiboDocument.Constants.CWConstant;
import com.ninghoo.beta17ma27.weydio2.WeiboDocument.Utils.SPUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

/**
 * Created by ningfu on 17-4-11.
 */

public class LandingActivity extends CommonActivity {
    private SsoHandler mSsoHandler;
    private AuthInfo mAuthInfo;
    private SPUtils mSPUtils;
    private ImageView ivLanding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); /*        getToolbar().hide();*/
        mAuthInfo = new AuthInfo(getApplicationContext(), CWConstant.APP_KEY, CWConstant.REDIRECT_URL, CWConstant.SCOPE);
        mSsoHandler = new SsoHandler(this, mAuthInfo);
        mSPUtils = SPUtils.getInstance(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkLogin();
            }
        }, 500);
        initialize();
    }

    private void checkLogin() { /*if(CWConstant.DEBUG_ACTIVITIES){ startActivity(new Intent(LandingPageActivity.this,DemoActivity.class)); finish(); return; }*/
        if (mSPUtils.isLogin()) {
            startActivity(new Intent(LandingActivity.this, MainActivity.class));
            finish();
        } else mSsoHandler.authorize(new WeiboAuthListener() {
            @Override
            public void onComplete(Bundle bundle) {
                Oauth2AccessToken token = Oauth2AccessToken.parseAccessToken(bundle);
                mSPUtils.saveToken(token);
                startActivity(new Intent(LandingActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onWeiboException(WeiboException e) {

            }

            @Override
            public void onCancel() {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != mSsoHandler) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private void initialize() {
        ivLanding = (ImageView) findViewById(R.id.ivLanding);
    }

    // 由于继承的是抽象类。所以需要重写抽象类方法。
    @Override
    public int getLayoutId() {
        return R.layout.ac_landing;
    }
}
