package com.unistrong.framwork.utils;

/**
 * 应用常量类
 */
public class Constant {
    public static final String UPLOAD_URL = "http://jwux.top:38080/upload/UploadServlet";
    public static final String PICTURE_HOST = "http://jwux.top:38028";

    /**
     * 接口名
     */
    public static class Action {
        public static final String LOGIN = "/user/login";
        public static final String QUERY_DICT = "/dic/getDictionaryList";
        public static final String QUERY_HOUSE_INFO = "/houseInfo/query";
        public static final String QUERY_PERSONS_INFO = "/person/query";
        public static final String QUERY_WINDOW_INFO = "/visitInfo/query";
        public static final String POST_WINDOW_INFO = "/visitInfo/registerData";
        public static final String UPDATE_WINDOW_INFO = "/visitInfo/update";
        public static final String QUERY_TASK_COUNT = "/subtask/getVisiteDetail";
        public static final String QUERY_TASK_LIST = "/subtask/queryByTaskId";
        public static final String GET_VERSION = "/appVersion/getLatestVersion";
    }

    public static class SP_KEY {
        public static final String USER_ACCOUNT = "user_account";
        public static final String USER_PWD = "user_password";
        public static final String TOKEN = "token";
        public static final String USER_ID = "user_id";

        //address info
        public static final String LAT = "latitude";
        public static final String LNG = "longitude";
        public static final String PROVINCE = "province";
        public static final String CITY = "city";
        public static final String DISTRICT = "district";
    }

}
