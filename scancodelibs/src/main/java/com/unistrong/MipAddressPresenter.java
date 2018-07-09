package com.unistrong;

import android.text.TextUtils;

import java.util.Map;

public class MipAddressPresenter {

    /**
     * 是有效二维码
     */
    public boolean isAvailableCode2(String resultStr) {
        return resultStr.indexOf("?") != -1 || resultStr.indexOf("@") != -1;
    }

    /**
     * 住宅编号
     */
    public String getHouseNum(String resultStr) {
        String num = "-1";
        if (resultStr.indexOf("?") != -1) {
            String[] str = resultStr.split("[?]");
            if (str != null && str.length > 0) {
                num = str[1];
            }
        } else if (resultStr.indexOf("@") != -1) {
            String[] str = resultStr.split("@");
            if (str != null && str.length > 0) {
                num = str[1];
            }
        }
        return num;
    }

    private String getWhereStr(String zzbh, Map<String, String> districtDict, String dwdm) {
        StringBuilder sb = new StringBuilder(0);
        sb.append("czbs < 3 and cxbs = 0");

        if (TextUtils.isEmpty(zzbh)) {
            sb.append(" and JLX='").append(districtDict.get(zzbh)).append("'");
        } else {
            sb.append(" and ZZBH='").append(zzbh).append("'");
            sb.append(" and SSSQ='").append(dwdm).append("'");
        }
        return sb.toString();
    }

}
