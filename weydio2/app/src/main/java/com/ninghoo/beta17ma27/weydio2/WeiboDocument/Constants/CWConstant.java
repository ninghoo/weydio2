package com.ninghoo.beta17ma27.weydio2.WeiboDocument.Constants;

/**
 * Created by ningfu on 17-4-13.
 */

public class CWConstant
{
    // 这里填写的是在微博审核应用页一样的APP_KEY
    public static final String APP_KEY = "3852198304";
    // 这里填写的不是MD5编码，而是微博审核应用页的App Secret：
    public static final String SECRET_KEY = "3508769f2d8aed0519834c62ed8ce0d6";
    // 微博审核应用页的授权页，可见，任填。
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

    public static final String SCOPE =                               // 应用申请的高级权限
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";

    public static final boolean DEBUG_ACTIVITIES = false;
}