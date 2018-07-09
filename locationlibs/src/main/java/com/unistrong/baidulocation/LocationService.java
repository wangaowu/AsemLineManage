package com.unistrong.baidulocation;

import android.app.Application;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 定位服务
 */
public class LocationService {
    private String TAG = getClass().getSimpleName();

    private static LocationService instance = null;

    private LocationClientOption option;
    private LocationClient locationClient;
    private BDAbstractLocationListener listener;

    public static LocationService getInstance(Application ctx) {
        if (instance == null) {
            instance = new LocationService(ctx);
        }
        return instance;
    }

    private LocationService(Application ctx) {
        locationClient = new LocationClient(ctx);
    }

    /**
     * 停止服务
     */
    public void stopLocation() {
        locationClient.stop();
        if (listener != null) {
            locationClient.unRegisterLocationListener(listener);
        }
    }

    /**
     * 开启服务
     *
     * @param milSeconds 为0时仅定位一次，>0有效
     */
    public void startLocation(int milSeconds, final OnReceiveInfo info) {
        if (locationClient.isStarted()) {
            stopLocation();
        }

        initOptions(milSeconds);
        locationClient.setLocOption(option);

        locationClient.registerLocationListener(listener = new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                Log.d(TAG, "onReceiveLocation: "
                        + bdLocation.getLatitude() + ","
                        + bdLocation.getLongitude());
                setListenerResult(bdLocation, info);
            }
        });
        locationClient.start();
    }

    /**
     * 设置结果监听
     *
     * @param bdLocation
     * @param info
     */
    private void setListenerResult(BDLocation bdLocation, OnReceiveInfo info) {
        if (info == null) {
            //不需要监听
            return;
        }
        if (bdLocation.getLocType() == BDLocation.TypeCriteriaException) {
            //定位异常
            return;
        }

        //国外
        boolean outCn = bdLocation.getLocType() == BDLocation.LOCATION_WHERE_OUT_CN;
        info.justOutCountry(outCn);
        if (outCn) return;
        //经纬度
        double latitude = bdLocation.getLatitude();
        double longitude = bdLocation.getLongitude();
        info.onReceive(latitude, longitude);
        //信息
        String province = bdLocation.getProvince();
        String city = bdLocation.getCity();
        String district = bdLocation.getDistrict();
        info.onReceive(province, city, district);
    }

    //配置参数
    private void initOptions(int milSeconds) {
        option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(milSeconds);
        option.setOpenGps(true);
        //位置改变忽略配置进行通知
        //option.setOpenAutoNotifyMode();
        option.setNeedDeviceDirect(false);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
    }

}
