package com.unistrong.asemlinemanage;

import android.util.Log;

import com.unistrong.baselibs.style.LogCrashApplication;
import com.unistrong.baselibs.utils.IToast;
import com.unistrong.framwork.utils.DeviceUtils;
import com.unistrong.requestlibs.request.BasePolicy;

public class Application extends LogCrashApplication {
    private static Application instance;

    public static Application getInstance() {
        return instance;
    }

    public static void setInstance(Application instance) {
        Application.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        IToast.init(this);
        BasePolicy.setAppInfoString(DeviceUtils.getAppInfo(this));
    }
}
