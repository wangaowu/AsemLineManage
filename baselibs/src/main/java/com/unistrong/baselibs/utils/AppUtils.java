package com.unistrong.baselibs.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import com.unistrong.baselibs.style.Activity_;

import java.util.LinkedList;
import java.util.List;

/**
 * 用于完全退出应用程序
 */
public class AppUtils {
    private static final String TAG = "AppUtils";

    public static List<Activity_> activityList = new LinkedList<Activity_>();
    private static AppUtils instance;

    /**
     * 单例模式中获取唯一的ExitAPPUtils实例
     */
    public static AppUtils getInstance() {
        if (null == instance) {
            instance = new AppUtils();
        }
        return instance;
    }

    public void addActivity(Activity_ activity_) {
        activityList.add(activity_);
    }

    public void removeActivity(Activity_ activity_) {
        activityList.remove(activity_);
    }

    public void popAllActivities(boolean finishSelf, String selfTag) {
        for (int i = 0; i < activityList.size(); i++) {
            Activity_ activity_ = activityList.get(i);
            boolean isSelf = selfTag.equals(activity_.TAG);
            if (isSelf && !finishSelf) {
                //是当前但不结束当前
                continue;
            }
            Log.i(TAG, "popActivity_TAG: " + activity_.TAG);
            activity_.finish();
        }
    }

    public static class Debug {
        private static Boolean isDebug = null;

        public static boolean isDebug() {
            return isDebug == null ? false : isDebug.booleanValue();
        }

        public static void initProcess(Context context) {
            if (isDebug == null) {
                isDebug = context.getApplicationInfo() != null &&
                        (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
            }
        }
    }

}
