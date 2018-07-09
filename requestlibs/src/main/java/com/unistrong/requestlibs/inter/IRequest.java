package com.unistrong.requestlibs.inter;

import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;


/**
 * 请求功能类
 */
public interface IRequest {
    /**
     * 请求方法
     */
    class Method {
        public static final int POST_AS_JSON = 1;
        public static final int POST_AS_FORM = 2;
        public static final int GET = 3;
    }

    /**
     * 请求
     * 指定tag取消请求
     *
     * @param action
     * @param params
     * @param timeout
     * @param tag
     * @param listener
     */
    void request(String action, int method, final Object params, int timeout, final long tag, ResponseBody listener);

    /**
     * get请求
     *
     * @param action
     * @param params
     * @param listener
     */
    void requestGet(String action, HashMap<String, String> params, ResponseBody listener);

    /**
     * post请求
     *
     * @param action
     * @param params
     * @param listener
     */
    void requestPost(String action, HashMap<String, String> params, ResponseBody listener);

    /**
     * post请求
     *
     * @param action
     * @param params
     * @param listener
     */
    void requestPostForm(String action, HashMap<String, String> params, ResponseBody listener);

    /**
     * 停止请求
     */
    void stopRequest();

    /**
     * 停止请求
     *
     * @param tag 该参数避免取消错乱，需在请求时作为final参数传入
     */
    void stopRequest(final long tag);

}
