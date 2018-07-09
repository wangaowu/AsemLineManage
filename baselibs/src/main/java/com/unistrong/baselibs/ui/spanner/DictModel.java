package com.unistrong.baselibs.ui.spanner;

import android.content.Context;
import android.support.annotation.ArrayRes;

/**
 * 字典匹配类
 */
public class DictModel {

    private static final String PREFIX = "=";

    private final String[] dictArray;

    public DictModel(Context ctx, @ArrayRes int arrayRes) {
        dictArray = ctx.getResources().getStringArray(arrayRes);
    }

    /**
     * 获取键名
     *
     * @param value 键值
     * @return
     */
    public String getAliasKey(String value) {
        for (String dictStr : dictArray) {
            String[] dict = dictStr.split(PREFIX);
            if (dict[1].equalsIgnoreCase(value)) {
                return dict[0];
            }
        }
        return getInitAliasKey();
    }

    /**
     * 获取键值
     *
     * @param aliasKey 键名
     * @return
     */
    public String getValue(String aliasKey) {
        for (String dictStr : dictArray) {
            String[] dict = dictStr.split(PREFIX);
            if (dict[0].equalsIgnoreCase(aliasKey)) {
                return dict[1];
            }
        }
        return getInitValue();
    }

    /**
     * 获取值数组
     */
    public String[] getValueArrays() {
        String[] valueArrays = new String[dictArray.length];
        int index = 0;
        for (String dictStr : dictArray) {
            String value = dictStr.split(PREFIX)[1];
            valueArrays[index] = value;
            index++;
        }
        return valueArrays;
    }

    /**
     * 默认key值
     *
     * @return
     */
    public String getInitAliasKey() {
        return dictArray[0].split(PREFIX)[0];
    }

    /**
     * 默认value值
     *
     * @return
     */
    public String getInitValue() {
        return dictArray[0].split(PREFIX)[1];
    }


}
