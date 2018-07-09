package com.unistrong.framwork.resp;

import java.util.List;

/**
 * 动态字典返回
 */
public class DictResp {

    /**
     * code : 0
     * msg : string
     * result : [{"dictCode":"string","dictName":"string"}]
     */

    private int code;
    private String msg;
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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * dictCode : string
         * dictName : string
         */

        private String dictCode;
        private String dictName;

        public String getDictCode() {
            return dictCode;
        }

        public void setDictCode(String dictCode) {
            this.dictCode = dictCode;
        }

        public String getDictName() {
            return dictName;
        }

        public void setDictName(String dictName) {
            this.dictName = dictName;
        }
    }
}
