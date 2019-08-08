package com.xinyartech.baselibrary.bean;

/**
 * Created by zhangjiebo on 2019/1/17.
 *
 * @author ZhangJieBo
 */
public class ConfigParam {

    /**
     * paramKey : Wilddog_URl
     * paramName : 野狗推送
     * paramValue : https://wd2313347259aylqzm.wilddogio.com/test/
     */

    private String paramKey;
    private String paramName;
    private String paramValue;

    public ConfigParam(String paramKey, String paramValue) {
        this.paramKey = paramKey;
        this.paramValue = paramValue;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}
