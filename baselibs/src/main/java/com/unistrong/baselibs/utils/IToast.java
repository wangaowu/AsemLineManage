package com.unistrong.baselibs.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 简化弹窗类
 */
public class IToast {

    private static Context context;

    public static void init(Context context) {
        IToast.context = context;
    }

    private static Toast toast;

    public static void toast(String message) {
        toast(message, false);
    }

    public static void toast(int messageResID) {
        toast(context.getString(messageResID), false);
    }

    public static void toast(String message, boolean lastLong) {
        int lastTime = lastLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, message, lastTime);
        toast.show();
    }

}
