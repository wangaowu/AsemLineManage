package com.unistrong.framwork.resp;

/**
 * 更新版本返回
 */
public class VersionResp {


    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {

        private String appPath;
        private String appVersionCode;
        private int appVersionId;
        private String appVersionName;

        public String getAppPath() {
            return appPath;
        }

        public void setAppPath(String appPath) {
            this.appPath = appPath;
        }

        public String getAppVersionCode() {
            return appVersionCode;
        }

        public void setAppVersionCode(String appVersionCode) {
            this.appVersionCode = appVersionCode;
        }

        public int getAppVersionId() {
            return appVersionId;
        }

        public void setAppVersionId(int appVersionId) {
            this.appVersionId = appVersionId;
        }

        public String getAppVersionName() {
            return appVersionName;
        }

        public void setAppVersionName(String appVersionName) {
            this.appVersionName = appVersionName;
        }
    }
}
