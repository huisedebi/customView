package com.xinyartech.baselibrary.bean;

import com.lzy.okgo.model.HttpHeaders;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/6/16 0016.
 */
public class OkObject {
    private HashMap<String, String> params;
    private String url;
    private HttpHeaders httpHeaders;
    private String cacheKey;

    public String getJson() {
        JSONObject jsonObject = new JSONObject(params);
        return jsonObject.toString();
    }

    public OkObject(HttpHeaders httpHeaders, HashMap<String, String> params, String url) {
        this.params = params;
        this.url = url;
        this.httpHeaders = httpHeaders;
    }

    public OkObject(HashMap<String, String> params, String url) {
        this.params = params;
        this.url = url;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }
}
