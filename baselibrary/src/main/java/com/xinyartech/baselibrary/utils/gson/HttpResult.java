package com.xinyartech.baselibrary.utils.gson;

/**
 * Created by zhangjiebo on 2018/3/19.
 *
 * @author ZhangJieBo
 */

public class HttpResult<T> {
    /**
     * state : 1
     * data :
     * msg : null
     */

    private int code;
    private String message;
    private T data;

    public T getResult() {
        return data;
    }

    public void setResult(T result) {
        this.data = result;
    }

    public int getStatus() {
        return code;
    }

    public void setStatus(int status) {
        this.code = status;
    }



    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

}
