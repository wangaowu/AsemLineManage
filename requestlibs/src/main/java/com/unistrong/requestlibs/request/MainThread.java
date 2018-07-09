package com.unistrong.requestlibs.request;

import android.os.Handler;

public class MainThread {
    public static void runOnUIThread(Handler handler, Runnable runnable) {
        handler.post(runnable);
    }
}
