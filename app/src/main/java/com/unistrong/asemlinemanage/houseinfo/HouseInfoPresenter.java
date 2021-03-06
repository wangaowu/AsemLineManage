package com.unistrong.asemlinemanage.houseinfo;

import android.content.Context;
import android.content.Intent;

import com.unistrong.asemlinemanage.updateinfo.UpdateHouseInfoActivity;
import com.unistrong.framwork.resp.CompanyInfoResp;
import com.unistrong.framwork.resp.WindowImageResp;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.framwork.utils.HttpRequestImpl;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;

public class HouseInfoPresenter {

    /**
     * 请求房屋基本信息
     *
     * @param houseId
     * @param listener
     */
    public void requestHouseBasicInfo(String houseId, ResponseBody listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("currentPage", "1");
        map.put("pageSize", "1");
        map.put("houseId", houseId);
        HttpRequestImpl.getInstance().requestPost(Constant.Action.QUERY_HOUSE_INFO, map, listener);
    }

    /**
     * 请求单位法人基本信息
     *
     * @param houseId
     * @param listener
     */
    public void requestCompanyBasicInfo(String houseId, ResponseBody<CompanyInfoResp> listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("currentPage", "1");
        map.put("pageSize", "1");
        map.put("houseId", houseId);
        HttpRequestImpl.getInstance().requestPost(Constant.Action.QUERY_COMPANY_INFO, map, listener);
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
     * 请求窗户图片信息
     *
     * @param visiteId
     * @param listener
     */
    public void requestWindowImageInfo(String visiteId, ResponseBody listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("visitId", visiteId);
        HttpRequestImpl.getInstance().requestGet(Constant.Action.QUERY_WINDOW_IMAGE, map, listener);
    }

    /**
     * 请求窗户走访信息
     *
     * @param houseId
     * @param subtaskId
     * @param listener
     */
    public void requestWindowInfo(String houseId, String subtaskId, String houseType, String visitId, ResponseBody listener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("currentPage", "1");
        map.put("pageSize", "10");
        map.put("houseId", houseId);
        map.put("subtaskId", subtaskId);
        map.put("houseType", houseType);
        map.put("visitId", visitId);
        HttpRequestImpl.getInstance().requestPost(Constant.Action.QUERY_WINDOW_INFO, map, listener);
    }

    /**
     * 打开修改走访信息界面
     *
     * @param context
     * @param bean
     */
    public void startUpdateHouseInfoActivity(Context context, WindowImageResp.ResultBean bean) {
        Intent intent = new Intent(context, UpdateHouseInfoActivity.class);
        intent.putExtra(UpdateHouseInfoActivity.INTENT_KEY, bean);
        context.startActivity(intent);
    }

}
