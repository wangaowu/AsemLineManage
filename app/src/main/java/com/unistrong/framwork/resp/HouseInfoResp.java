package com.unistrong.framwork.resp;

import java.util.List;

/**
 * 房屋基本信息
 */
public class HouseInfoResp {

    /**
     * code : 1
     * msg :
     * result : [{"houseId":"D6501025412052813382","streetNo":"650102000006","streetName":"新华南路 ","buildNo":"4","buildSuffixNo":"6","unitNo":"01","floorNo":"1","floorSuffixNo":"1","roomNo":"301","roomSuffixNo":"2","houseNo":"1055","villageCode":"6501055490000008","villageName":"兴亚小区","officeCode":"650102540500","officeName":"乌鲁木齐市公安局天山区分局西河坝派出所团结西路社区警务室","policeCode":"650102540500","policeName":"乌鲁木齐市公安局天山区分局西河坝派出所团结西路社区警务室","communityCode":"6501029Q0604","communityName":"团结西路社区","communityManager":null,"communityManagerTel":null,"houseAddress":"新疆乌鲁木齐市天山区新华南路1055号4号楼一单元301号","latitude":43.77368,"longtitude":87.60672,"houseQrUrl":"","houseType":"rent","policeManager":"","policeManagerTel":"","houseRoomStructure":null,"houseRoomCount":null}]
     * total : 1
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

    public static class ResultBean {
        /**
         * houseId : D6501025412052813382
         * streetNo : 650102000006
         * streetName : 新华南路
         * buildNo : 4
         * buildSuffixNo : 6
         * unitNo : 01
         * floorNo : 1
         * floorSuffixNo : 1
         * roomNo : 301
         * roomSuffixNo : 2
         * houseNo : 1055
         * villageCode : 6501055490000008
         * villageName : 兴亚小区
         * officeCode : 650102540500
         * officeName : 乌鲁木齐市公安局天山区分局西河坝派出所团结西路社区警务室
         * policeCode : 650102540500
         * policeName : 乌鲁木齐市公安局天山区分局西河坝派出所团结西路社区警务室
         * communityCode : 6501029Q0604
         * communityName : 团结西路社区
         * communityManager : null
         * communityManagerTel : null
         * houseAddress : 新疆乌鲁木齐市天山区新华南路1055号4号楼一单元301号
         * latitude : 43.77368
         * longtitude : 87.60672
         * houseQrUrl :
         * houseType : rent
         * policeManager :
         * policeManagerTel :
         * houseRoomStructure : null
         * houseRoomCount : null
         */

        private String houseId;
        private String streetNo;
        private String streetName;
        private String buildNo;
        private String buildSuffixNo;
        private String unitNo;
        private String floorNo;
        private String floorSuffixNo;
        private String roomNo;
        private String roomSuffixNo;
        private String houseNo;
        private String villageCode;
        private String villageName;
        private String officeCode;
        private String officeName;
        private String policeCode;
        private String policeName;
        private String communityCode;
        private String communityName;
        private String communityManager;
        private String communityManagerTel;
        private String houseAddress;
        private double latitude;
        private double longtitude;
        private String houseQrUrl;
        private String houseType;
        private String policeManager;
        private String policeManagerTel;
        private String houseRoomStructure;
        private String houseRoomCount;

        public String getHouseId() {
            return houseId;
        }

        public void setHouseId(String houseId) {
            this.houseId = houseId;
        }

        public String getStreetNo() {
            return streetNo;
        }

        public void setStreetNo(String streetNo) {
            this.streetNo = streetNo;
        }

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public String getBuildNo() {
            return buildNo;
        }

        public void setBuildNo(String buildNo) {
            this.buildNo = buildNo;
        }

        public String getBuildSuffixNo() {
            return buildSuffixNo;
        }

        public void setBuildSuffixNo(String buildSuffixNo) {
            this.buildSuffixNo = buildSuffixNo;
        }

        public String getUnitNo() {
            return unitNo;
        }

        public void setUnitNo(String unitNo) {
            this.unitNo = unitNo;
        }

        public String getFloorNo() {
            return floorNo;
        }

        public void setFloorNo(String floorNo) {
            this.floorNo = floorNo;
        }

        public String getFloorSuffixNo() {
            return floorSuffixNo;
        }

        public void setFloorSuffixNo(String floorSuffixNo) {
            this.floorSuffixNo = floorSuffixNo;
        }

        public String getRoomNo() {
            return roomNo;
        }

        public void setRoomNo(String roomNo) {
            this.roomNo = roomNo;
        }

        public String getRoomSuffixNo() {
            return roomSuffixNo;
        }

        public void setRoomSuffixNo(String roomSuffixNo) {
            this.roomSuffixNo = roomSuffixNo;
        }

        public String getHouseNo() {
            return houseNo;
        }

        public void setHouseNo(String houseNo) {
            this.houseNo = houseNo;
        }

        public String getVillageCode() {
            return villageCode;
        }

        public void setVillageCode(String villageCode) {
            this.villageCode = villageCode;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public String getOfficeCode() {
            return officeCode;
        }

        public void setOfficeCode(String officeCode) {
            this.officeCode = officeCode;
        }

        public String getOfficeName() {
            return officeName;
        }

        public void setOfficeName(String officeName) {
            this.officeName = officeName;
        }

        public String getPoliceCode() {
            return policeCode;
        }

        public void setPoliceCode(String policeCode) {
            this.policeCode = policeCode;
        }

        public String getPoliceName() {
            return policeName;
        }

        public void setPoliceName(String policeName) {
            this.policeName = policeName;
        }

        public String getCommunityCode() {
            return communityCode;
        }

        public void setCommunityCode(String communityCode) {
            this.communityCode = communityCode;
        }

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public String getCommunityManager() {
            return communityManager;
        }

        public void setCommunityManager(String communityManager) {
            this.communityManager = communityManager;
        }

        public String getCommunityManagerTel() {
            return communityManagerTel;
        }

        public void setCommunityManagerTel(String communityManagerTel) {
            this.communityManagerTel = communityManagerTel;
        }

        public String getHouseAddress() {
            return houseAddress;
        }

        public void setHouseAddress(String houseAddress) {
            this.houseAddress = houseAddress;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongtitude() {
            return longtitude;
        }

        public void setLongtitude(double longtitude) {
            this.longtitude = longtitude;
        }

        public String getHouseQrUrl() {
            return houseQrUrl;
        }

        public void setHouseQrUrl(String houseQrUrl) {
            this.houseQrUrl = houseQrUrl;
        }

        public String getHouseType() {
            if ("rent".equals(houseType)) {
                return "租住";
            } else {
                return "办公";
            }
        }

        public void setHouseType(String houseType) {
            this.houseType = houseType;
        }

        public String getPoliceManager() {
            return policeManager;
        }

        public void setPoliceManager(String policeManager) {
            this.policeManager = policeManager;
        }

        public String getPoliceManagerTel() {
            return policeManagerTel;
        }

        public void setPoliceManagerTel(String policeManagerTel) {
            this.policeManagerTel = policeManagerTel;
        }

        public String getHouseRoomStructure() {
            return houseRoomStructure;
        }

        public void setHouseRoomStructure(String houseRoomStructure) {
            this.houseRoomStructure = houseRoomStructure;
        }

        public String getHouseRoomCount() {
            return houseRoomCount;
        }

        public void setHouseRoomCount(String houseRoomCount) {
            this.houseRoomCount = houseRoomCount;
        }
    }
}
