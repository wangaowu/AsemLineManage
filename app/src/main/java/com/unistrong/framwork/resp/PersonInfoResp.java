package com.unistrong.framwork.resp;

import java.util.List;

/**
 * 人员信息
 */
public class PersonInfoResp {

    /**
     * code : 0
     * msg : string
     * result : [{"birth":"2018-06-13T05:20:40.349Z","birthAddress":"string","birthOfficeCode":"string","birthPoliceStation":"string","cardType":"string","houseId":"string","idcard":"string","liveAddress":"string","liveOfficeCode":"string","livePoliceStation":"string","name":"string","nation":"string","personNo":"string","personType":"string","sex":"string","telephone":"string"}]
     * total : 0
     */

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
        /**
         * birth : 2018-06-13T05:20:40.349Z
         * birthAddress : string
         * birthOfficeCode : string
         * birthPoliceStation : string
         * cardType : string
         * houseId : string
         * idcard : string
         * liveAddress : string
         * liveOfficeCode : string
         * livePoliceStation : string
         * name : string
         * nation : string
         * personNo : string
         * personType : string
         * sex : string
         * telephone : string
         */

        private String birth;
        private String birthAddress;
        private String birthOfficeCode;
        private String birthPoliceStation;
        private String cardType;
        private String houseId;
        private String idcard;
        private String liveAddress;
        private String liveOfficeCode;
        private String livePoliceStation;
        private String name;
        private String nation;
        private String personNo;
        private String personType;
        private String sex;
        private String telephone;

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getBirthAddress() {
            return birthAddress;
        }

        public void setBirthAddress(String birthAddress) {
            this.birthAddress = birthAddress;
        }

        public String getBirthOfficeCode() {
            return birthOfficeCode;
        }

        public void setBirthOfficeCode(String birthOfficeCode) {
            this.birthOfficeCode = birthOfficeCode;
        }

        public String getBirthPoliceStation() {
            return birthPoliceStation;
        }

        public void setBirthPoliceStation(String birthPoliceStation) {
            this.birthPoliceStation = birthPoliceStation;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getHouseId() {
            return houseId;
        }

        public void setHouseId(String houseId) {
            this.houseId = houseId;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getLiveAddress() {
            return liveAddress;
        }

        public void setLiveAddress(String liveAddress) {
            this.liveAddress = liveAddress;
        }

        public String getLiveOfficeCode() {
            return liveOfficeCode;
        }

        public void setLiveOfficeCode(String liveOfficeCode) {
            this.liveOfficeCode = liveOfficeCode;
        }

        public String getLivePoliceStation() {
            return livePoliceStation;
        }

        public void setLivePoliceStation(String livePoliceStation) {
            this.livePoliceStation = livePoliceStation;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getPersonNo() {
            return personNo;
        }

        public void setPersonNo(String personNo) {
            this.personNo = personNo;
        }

        public String getPersonType() {
            return personType;
        }

        public void setPersonType(String personType) {
            this.personType = personType;
        }

        public String getSex() {
            if ("1".equals(sex)) {
                return "男";
            } else {
                return "女";
            }
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
    }
}
