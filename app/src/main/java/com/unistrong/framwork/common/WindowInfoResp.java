package com.unistrong.framwork.common;

import java.util.List;

/**
 * 走访信息返回
 */
public class WindowInfoResp {

    private int code;
    private String msg;
    private int total;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String abnomal;
        private String abnomalReason;
        private String houseId;
        private String notify;
        private String renterChange;
        private String structChange;
        private String structChangePicurl;
        private String structChangeReason;
        private String visitId;
        private String visiteTime;
        private String visiteUser;
        private int visiteUserId;
        private String isEnter;

        public String getAbnomal() {
            return abnomal;
        }

        public void setAbnomal(String abnomal) {
            this.abnomal = abnomal;
        }

        public String getAbnomalReason() {
            return abnomalReason;
        }

        public void setAbnomalReason(String abnomalReason) {
            this.abnomalReason = abnomalReason;
        }

        public String getHouseId() {
            return houseId;
        }

        public void setHouseId(String houseId) {
            this.houseId = houseId;
        }

        public String getNotify() {
            return notify;
        }

        public void setNotify(String notify) {
            this.notify = notify;
        }

        public String getRenterChange() {
            return renterChange;
        }

        public void setRenterChange(String renterChange) {
            this.renterChange = renterChange;
        }

        public String getStructChange() {
            return structChange;
        }

        public void setStructChange(String structChange) {
            this.structChange = structChange;
        }

        public String getStructChangePicurl() {
            return structChangePicurl;
        }

        public void setStructChangePicurl(String structChangePicurl) {
            this.structChangePicurl = structChangePicurl;
        }

        public String getStructChangeReason() {
            return structChangeReason;
        }

        public void setStructChangeReason(String structChangeReason) {
            this.structChangeReason = structChangeReason;
        }

        public String getVisitId() {
            return visitId;
        }

        public void setVisitId(String visitId) {
            this.visitId = visitId;
        }

        public String getVisiteTime() {
            return visiteTime;
        }

        public void setVisiteTime(String visiteTime) {
            this.visiteTime = visiteTime;
        }

        public String getVisiteUser() {
            return visiteUser;
        }

        public void setVisiteUser(String visiteUser) {
            this.visiteUser = visiteUser;
        }

        public int getVisiteUserId() {
            return visiteUserId;
        }

        public void setVisiteUserId(int visiteUserId) {
            this.visiteUserId = visiteUserId;
        }

        public String getIsEnter() {
            return isEnter;
        }

        public void setIsEnter(String isEnter) {
            this.isEnter = isEnter;
        }
    }
}
