package com.unistrong;

import android.content.Intent;
import android.text.TextUtils;

import com.unistrong.baselibs.utils.IToast;

import static android.app.Activity.RESULT_OK;

/**
 * 二维码地址处理工具类
 */
public class MipAddressUtils {

    /**
     * 获取房屋id
     *
     * @param resultCode activity返回码
     * @param data       activity返回数据
     * @return
     */
    public static String getHouseIdString(int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return null;

        String resultStr;
        if (TextUtils.isEmpty(resultStr = data.getExtras().getString("result"))) {
            IToast.toast("二维码扫描为空！");
            return null;
        }
        return resultStr;
    }


}
