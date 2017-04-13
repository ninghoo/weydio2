package com.ninghoo.beta17ma27.weydio2.WeiboDocument.Utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by ningfu on 17-4-12.
 */

public class LogUtils
{
    private static final String TAG = "cniao5";
    public static void e(String message){
        if(!TextUtils.isEmpty(message)){
            Log.e(TAG,message);
        }
    }
    public static void d(String message){
        if(!TextUtils.isEmpty(message)){
            Log.d(TAG, message);
        }
    }
    public static void o(String tag,String message){
        if(!TextUtils.isEmpty(message)){
            Log.d(tag,message);
        }
    }
}
