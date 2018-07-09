package com.unistrong.framwork.utils;

import com.unistrong.baselibs.utils.IToast;
import com.unistrong.framwork.resp.DictResp;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 字典<后续使用库></>
 */
public class DynamicDictUtils {
    public static DynamicDictUtils instance;
    public static List<DictResp.ResultBean> houseCateList;
    public static List<DictResp.ResultBean> windowDirectionList;

    public static DynamicDictUtils getInstance() {
        return instance == null ? new DynamicDictUtils() : instance;
    }

    public String getValue(String key, List<DictResp.ResultBean> dictList) {
        if (dictList != null) {
            for (DictResp.ResultBean bean : dictList) {
                if (bean.getDictCode().equals(key)) {
                    return bean.getDictName();
                }
            }
        }
        return "-";
    }

    public String getKey(String value, List<DictResp.ResultBean> dictList) {
        if (dictList != null) {
            for (DictResp.ResultBean bean : dictList) {
                if (bean.getDictName().equals(value)) {
                    return bean.getDictCode();
                }
            }
        }
        return "-";
    }

    public String[] getValueArray(List<DictResp.ResultBean> dictList) {
        String[] result = new String[0];
        if (dictList != null) {
            result = new String[dictList.size()];
            int index = 0;
            for (DictResp.ResultBean bean : dictList) {
                result[index] = bean.getDictName();
                index++;
            }
        }
        return result;
    }

    /**
     * 初始化动态字典
     */
    public void init() {
        HashMap<String, String> params = new HashMap<>();
        params.put("dictNo", "HW01");
        HttpRequestImpl.getInstance().requestGet(Constant.Action.QUERY_DICT, params, new ResponseBody<DictResp>(DictResp.class) {
            @Override
            public void onFailure(String message) {
            }

            @Override
            public void onSuccess(DictResp resp) {
                if (isFailure(resp.getCode())) {
                    IToast.toast(resp.getMsg());
                    return;
                }
                houseCateList = resp.getResult();
            }
        });
        HashMap<String, String> params1 = new HashMap<>();
        params1.put("dictNo", "HW02");
        HttpRequestImpl.getInstance().requestGet(Constant.Action.QUERY_DICT, params1, new ResponseBody<DictResp>(DictResp.class) {
            @Override
            public void onFailure(String message) {
            }

            @Override
            public void onSuccess(DictResp resp) {
                if (isFailure(resp.getCode())) {
                    IToast.toast(resp.getMsg());
                    return;
                }
                windowDirectionList = resp.getResult();
            }
        });
    }
}
