package com.unistrong.baidulocation;

/**
 * 定位结果回调
 */
public interface OnReceiveInfo {
    /**
     * 解析信息
     *
     * @param province
     * @param city
     * @param district
     */
    void onReceive(String province, String city, String district);

    /**
     * 经纬度
     */
    void onReceive(double lat, double lng);

    /**
     * 国外
     */
    void justOutCountry(boolean out);
}
