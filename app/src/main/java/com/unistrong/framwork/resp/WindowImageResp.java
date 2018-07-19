package com.unistrong.framwork.resp;

import java.io.Serializable;
import java.util.List;

/**
 * 房屋照片响应体
 */
public class WindowImageResp {

    /**
     * code : 1
     * msg :
     * result : [{"visiteDetailId":"0e762ed00d254efbaa96aa24d3acc2d1","visiteId":"092771ef89fa429283c53462f07565eb","windowType":"客厅","windowDirection":"西","windowDesc":"","windowPicurl":"http://jwux.top:38028/u/0/0/201806/o/9229624aebcd42eda60de6d17390f3d0.jpg"},{"visiteDetailId":"38bc9bdb746d4b6d908e398a5b10ea7d","visiteId":"092771ef89fa429283c53462f07565eb","windowType":"次卧2","windowDirection":"东北","windowDesc":"","windowPicurl":"http://jwux.top:38028/u/0/0/201806/o/dda4442c8efb480badf19c271196bf8d.jpg"},{"visiteDetailId":"b8ea0f668e2a452db4661eb38543153c","visiteId":"092771ef89fa429283c53462f07565eb","windowType":"厨房1","windowDirection":"西南","windowDesc":"窗户正常","windowPicurl":"http://jwux.top:38028/u/0/0/201806/o/65f387dc656346a5a5b1ec95065477f8.jpg"}]
     * ok : true
     */

    private int code;
    private String msg;
    private boolean ok;
    private List<ResultBean> result;

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

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * visiteDetailId : 0e762ed00d254efbaa96aa24d3acc2d1
         * visiteId : 092771ef89fa429283c53462f07565eb
         * windowType : 客厅
         * windowDirection : 西
         * windowDesc :
         * windowPicurl : http://jwux.top:38028/u/0/0/201806/o/9229624aebcd42eda60de6d17390f3d0.jpg
         */

        private String visiteDetailId;
        private String visiteId;
        private String windowType;
        private String windowDirection;
        private String windowDesc;
        private String windowPicurl;

        public String getVisiteDetailId() {
            return visiteDetailId;
        }

        public void setVisiteDetailId(String visiteDetailId) {
            this.visiteDetailId = visiteDetailId;
        }

        public String getVisiteId() {
            return visiteId;
        }

        public void setVisiteId(String visiteId) {
            this.visiteId = visiteId;
        }

        public String getWindowType() {
            return windowType;
        }

        public void setWindowType(String windowType) {
            this.windowType = windowType;
        }

        public String getWindowDirection() {
            return windowDirection;
        }

        public void setWindowDirection(String windowDirection) {
            this.windowDirection = windowDirection;
        }

        public String getWindowDesc() {
            return windowDesc;
        }

        public void setWindowDesc(String windowDesc) {
            this.windowDesc = windowDesc;
        }

        public String getWindowPicurl() {
            return windowPicurl;
        }

        public void setWindowPicurl(String windowPicurl) {
            this.windowPicurl = windowPicurl;
        }
    }
}
