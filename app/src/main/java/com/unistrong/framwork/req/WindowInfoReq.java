package com.unistrong.framwork.req;

import com.google.gson.annotations.Expose;

import java.util.List;

/***
 * 走访录入的信息
 */
public class WindowInfoReq {

    private String taskId; //任务id
    private String subtaskId; //子任务id
    private String abnomal; //异常
    private String abnomalReason;//异常原因
    private String houseId;//房屋id
    private String notify;//告知窗户不能打开
    private String renterChange;//承租人改变
    private String structChange;//结构改变
    private String structChangePicurl;//房屋结构改变照片
    private String structChangeReason;//结构改变详情
    private List<AddVisitDetailsBean> addVisitDetails;
    private String isEnter;//是否屋内信息
    private String visitType;//房屋类型
    //单位异常
    private String securityCheck;//安防检查是否异常
    private String securityAbnomalReason;//安防检查异常原因描述
    private String fireControlCheck;//消防检查是否异常
    private String fireControlAbnomalReason;//消防检查异常原因描述
    private String peopleCheck;//人员检查是否异常
    private String peopleAbnomalReason;//人员检查是异常原因描述

    public String getSecurityCheck() {
        return securityCheck;
    }

    public void setSecurityCheck(String securityCheck) {
        this.securityCheck = securityCheck;
    }

    public String getSecurityAbnomalReason() {
        return securityAbnomalReason;
    }

    public void setSecurityAbnomalReason(String securityAbnomalReason) {
        this.securityAbnomalReason = securityAbnomalReason;
    }

    public String getFireControlCheck() {
        return fireControlCheck;
    }

    public void setFireControlCheck(String fireControlCheck) {
        this.fireControlCheck = fireControlCheck;
    }

    public String getFireControlAbnomalReason() {
        return fireControlAbnomalReason;
    }

    public void setFireControlAbnomalReason(String fireControlAbnomalReason) {
        this.fireControlAbnomalReason = fireControlAbnomalReason;
    }

    public String getPeopleCheck() {
        return peopleCheck;
    }

    public void setPeopleCheck(String peopleCheck) {
        this.peopleCheck = peopleCheck;
    }

    public String getPeopleAbnomalReason() {
        return peopleAbnomalReason;
    }

    public void setPeopleAbnomalReason(String peopleAbnomalReason) {
        this.peopleAbnomalReason = peopleAbnomalReason;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(String subtaskId) {
        this.subtaskId = subtaskId;
    }

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

    public List<AddVisitDetailsBean> getAddVisitDetails() {
        return addVisitDetails;
    }

    public void setAddVisitDetails(List<AddVisitDetailsBean> addVisitDetails) {
        this.addVisitDetails = addVisitDetails;
    }

    public String getIsEnter() {
        return isEnter;
    }

    public void setIsEnter(String isEnter) {
        this.isEnter = isEnter;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public static class AddVisitDetailsBean {

        private String windowDesc;//窗户描述
        private String windowDirection;//窗户朝向
        private String windowPicurl;//窗户照片
        private String windowType;//房间类型

        private String visiteDetailId;
        private String visiteId;

        @Expose(serialize = false, deserialize = false)
        private String windowImagePath;//窗户本地路径(非结构内字段，为过渡)

        public AddVisitDetailsBean(String windowImagePath, String windowType, String windowDirection) {
            this.windowImagePath = windowImagePath;
            this.windowType = windowType;
            this.windowDirection = windowDirection;
        }

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

        public String getWindowDesc() {
            return windowDesc;
        }

        public void setWindowDesc(String windowDesc) {
            this.windowDesc = windowDesc;
        }

        public String getWindowDirection() {
            return windowDirection;
        }

        public void setWindowDirection(String windowDirection) {
            this.windowDirection = windowDirection;
        }

        public String getWindowPicurl() {
            return windowPicurl;
        }

        public void setWindowPicurl(String windowPicurl) {
            this.windowPicurl = windowPicurl;
        }

        public String getWindowType() {
            return windowType;
        }

        public void setWindowType(String windowType) {
            this.windowType = windowType;
        }

        public String getWindowImagePath() {
            return windowImagePath;
        }

        public void setWindowImagePath(String windowImagePath) {
            this.windowImagePath = windowImagePath;
        }
    }
}
