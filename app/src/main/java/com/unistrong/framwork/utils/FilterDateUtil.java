package com.unistrong.framwork.utils;

import android.text.TextUtils;

public class FilterDateUtil {
    /**
     * 过滤时间格式
     *
     * @param src
     * @return
     */
    public static String filter(String src) {
        if (TextUtils.isEmpty(src)) return "-";
        if (!src.contains(".")) return src;
        return src.split("\\.")[0];
    }
}
