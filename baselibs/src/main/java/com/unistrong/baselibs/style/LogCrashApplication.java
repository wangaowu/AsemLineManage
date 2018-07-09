package com.unistrong.baselibs.style;

import com.PictureApplication;
import com.unistrong.baselibs.utils.AppUtils;
import com.unistrong.baselibs.utils.CrashHandler;

public class LogCrashApplication extends PictureApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.Debug.initProcess(this);

        if (!isDebugMode()) {
            CrashHandler.init(this);
        }
    }

    private boolean isDebugMode() {
        return AppUtils.Debug.isDebug();
    }
}
