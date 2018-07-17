package com.unistrong.asemlinemanage.mytask;

import android.content.Context;

import com.unistrong.baselibs.utils.SPUtils;
import com.unistrong.framwork.utils.Constant;
import com.unistrong.framwork.utils.HttpRequestImpl;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;

public class MyTaskPresenter {

    private Context context;

    public MyTaskPresenter(Context context) {
        this.context = context;
    }

    /**
     * 请求任务列表
     *
     * @param status      任务状态
     * @param currentPage 当前页码
     * @param houseId     房屋id
     * @param houseType   房屋类型
     * @param listener    返回体
     */
    public void requestTaskList(String status, int currentPage, String houseId, String houseType, ResponseBody listener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("currentPage", String.valueOf(currentPage));
        params.put("subtaskStatus", status);
        params.put("pageSize", String.valueOf(15));
        params.put("houseId", houseId);
        params.put("houseType", houseType);
        params.put("officeCode", SPUtils.getString(context, Constant.SP_KEY.OFFICE_CODE));
        HttpRequestImpl.getInstance().requestPost(Constant.Action.QUERY_TASK_LIST, params, listener);
    }
}
