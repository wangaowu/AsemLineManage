package com.unistrong.asemlinemanage.houseinfo;

import android.content.Context;
import android.content.Intent;

import com.unistrong.asemlinemanage.updateinfo.UpdateHouseInfoActivity;
import com.unistrong.framwork.common.WindowInfoResp;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.framwork.utils.HttpRequestImpl;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;

public class HouseInfoPresenter {

    /**
     * 请求基本信息
     *
     * @param houseId
     * @param listener
     */
    public void requestBasicInfo(String houseId, ResponseBody listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("currentPage", "1");
        map.put("pageSize", "1");
        map.put("houseId", houseId);
        HttpRequestImpl.getInstance().requestPost(Constant.Action.QUERY_HOUSE_INFO, map, listener);
    }

    /**
     * 请求基本信息
     *
     * @param houseId
     * @param listener
     */
    public void requestPersonInfo(String houseId, ResponseBody listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("currentPage", "1");
        map.put("pageSize", "10");
        map.put("houseId", houseId);
        HttpRequestImpl.getInstance().requestPost(Constant.Action.QUERY_PERSONS_INFO, map, listener);
    }

    /**
     * 请求窗户照片信息
     *
     * @param houseId
     * @param subtaskId
     * @param listener
     */
    public void requestHouseImageInfo(String houseId, String subtaskId, ResponseBody listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("currentPage", "1");
        map.put("pageSize", "10");
        map.put("houseId", houseId);
        map.put("subtaskId", subtaskId);
        HttpRequestImpl.getInstance().requestPost(Constant.Action.QUERY_WINDOW_INFO, map, listener);
    }

    /**
     * 打开修改走访信息界面
     *
     * @param context
     * @param bean
     */
    public void startUpdateHouseInfoActivity(Context context, WindowInfoResp.ResultBean.VisitDetailListBean bean) {
        Intent intent = new Intent(context, UpdateHouseInfoActivity.class);
        intent.putExtra(UpdateHouseInfoActivity.INTENT_KEY, bean);
        context.startActivity(intent);
    }

}
