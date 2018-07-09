package com.unistrong.framwork.resp;

import java.io.Serializable;
import java.util.List;

/**
 * 任务列表返回
 */
public class TaskListResp {

    /**
     * code : 1
     * msg :
     * result : [{"subtaskId":"8","taskId":"3","houseId":"Z6501055112060604140","houseAddress":"新疆乌鲁木齐市水磨沟区沿河路东一巷59号210号楼二单元401号","subtaskCommunity":"650105531600","subtaskCommunityName":"社区2","subtaskFinishTime":"2018-06-13 13:20:50.0","subtaskUserId":2,"subtaskUserName":"路飞","subtaskStatus":"finished","taskEquipment":null,"taskName":"测试3","taskDesc":"测试3","taskStartTime":"2018-01-01 12:00:00.0","taskEndTime":"2018-01-05 12:00:00.0","taskCreateTime":"2018-06-11 18:46:45.0"},{"subtaskId":"3","taskId":"2","houseId":null,"houseAddress":null,"subtaskCommunity":"650105531600","subtaskCommunityName":"社区2","subtaskFinishTime":null,"subtaskUserId":3,"subtaskUserName":"王高武","subtaskStatus":"created","taskEquipment":null,"taskName":"测试11","taskDesc":"测11111111111","taskStartTime":"2018-01-01 12:00:00.0","taskEndTime":"2018-01-05 12:00:00.0","taskCreateTime":"2018-06-11 17:14:31.0"},{"subtaskId":"4","taskId":"2","houseId":null,"houseAddress":null,"subtaskCommunity":"650105531600","subtaskCommunityName":"社区2","subtaskFinishTime":null,"subtaskUserId":3,"subtaskUserName":"王高武","subtaskStatus":"created","taskEquipment":null,"taskName":"测试11","taskDesc":"测11111111111","taskStartTime":"2018-01-01 12:00:00.0","taskEndTime":"2018-01-05 12:00:00.0","taskCreateTime":"2018-06-11 17:14:31.0"},{"subtaskId":"10","taskId":"3","houseId":null,"houseAddress":null,"subtaskCommunity":"650105531600","subtaskCommunityName":"社区2","subtaskFinishTime":null,"subtaskUserId":null,"subtaskUserName":null,"subtaskStatus":"created","taskEquipment":null,"taskName":"测试3","taskDesc":"测试3","taskStartTime":"2018-01-01 12:00:00.0","taskEndTime":"2018-01-05 12:00:00.0","taskCreateTime":"2018-06-11 18:46:45.0"},{"subtaskId":"9","taskId":"3","houseId":"D6501055712052435549","houseAddress":"新疆乌鲁木齐市水磨沟区东八家户街南四巷1号独楼一单元105号熊永全自建房","subtaskCommunity":"650105531600","subtaskCommunityName":"社区2","subtaskFinishTime":null,"subtaskUserId":null,"subtaskUserName":null,"subtaskStatus":"created","taskEquipment":null,"taskName":"测试3","taskDesc":"测试3","taskStartTime":"2018-01-01 12:00:00.0","taskEndTime":"2018-01-05 12:00:00.0","taskCreateTime":"2018-06-11 18:46:45.0"}]
     * total : 5
     * ok : true
     */

    private int code;
    private String msg;
    private int total;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
         * subtaskId : 8
         * taskId : 3
         * houseId : Z6501055112060604140
         * houseAddress : 新疆乌鲁木齐市水磨沟区沿河路东一巷59号210号楼二单元401号
         * subtaskCommunity : 650105531600
         * subtaskCommunityName : 社区2
         * subtaskFinishTime : 2018-06-13 13:20:50.0
         * subtaskUserId : 2
         * subtaskUserName : 路飞
         * subtaskStatus : finished
         * taskEquipment : null
         * taskName : 测试3
         * taskDesc : 测试3
         * taskStartTime : 2018-01-01 12:00:00.0
         * taskEndTime : 2018-01-05 12:00:00.0
         * taskCreateTime : 2018-06-11 18:46:45.0
         */

        private String subtaskId;
        private String taskId;
        private String houseId;
        private String houseAddress;
        private String subtaskCommunity;
        private String subtaskCommunityName;
        private String subtaskFinishTime;
        private int subtaskUserId;
        private String subtaskUserName;
        private String subtaskStatus;
        private Object taskEquipment;
        private String taskName;
        private String taskDesc;
        private String taskStartTime;
        private String taskEndTime;
        private String taskCreateTime;

        public String getSubtaskId() {
            return subtaskId;
        }

        public void setSubtaskId(String subtaskId) {
            this.subtaskId = subtaskId;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getHouseId() {
            return houseId;
        }

        public void setHouseId(String houseId) {
            this.houseId = houseId;
        }

        public String getHouseAddress() {
            return houseAddress;
        }

        public void setHouseAddress(String houseAddress) {
            this.houseAddress = houseAddress;
        }

        public String getSubtaskCommunity() {
            return subtaskCommunity;
        }

        public void setSubtaskCommunity(String subtaskCommunity) {
            this.subtaskCommunity = subtaskCommunity;
        }

        public String getSubtaskCommunityName() {
            return subtaskCommunityName;
        }

        public void setSubtaskCommunityName(String subtaskCommunityName) {
            this.subtaskCommunityName = subtaskCommunityName;
        }

        public String getSubtaskFinishTime() {
            return subtaskFinishTime;
        }

        public void setSubtaskFinishTime(String subtaskFinishTime) {
            this.subtaskFinishTime = subtaskFinishTime;
        }

        public int getSubtaskUserId() {
            return subtaskUserId;
        }

        public void setSubtaskUserId(int subtaskUserId) {
            this.subtaskUserId = subtaskUserId;
        }

        public String getSubtaskUserName() {
            return subtaskUserName;
        }

        public void setSubtaskUserName(String subtaskUserName) {
            this.subtaskUserName = subtaskUserName;
        }

        public String getSubtaskStatus() {
            return subtaskStatus;
        }

        public void setSubtaskStatus(String subtaskStatus) {
            this.subtaskStatus = subtaskStatus;
        }

        public Object getTaskEquipment() {
            return taskEquipment;
        }

        public void setTaskEquipment(Object taskEquipment) {
            this.taskEquipment = taskEquipment;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskDesc() {
            return taskDesc;
        }

        public void setTaskDesc(String taskDesc) {
            this.taskDesc = taskDesc;
        }

        public String getTaskStartTime() {
            return taskStartTime;
        }

        public void setTaskStartTime(String taskStartTime) {
            this.taskStartTime = taskStartTime;
        }

        public String getTaskEndTime() {
            return taskEndTime;
        }

        public void setTaskEndTime(String taskEndTime) {
            this.taskEndTime = taskEndTime;
        }

        public String getTaskCreateTime() {
            return taskCreateTime;
        }

        public void setTaskCreateTime(String taskCreateTime) {
            this.taskCreateTime = taskCreateTime;
        }
    }
}
