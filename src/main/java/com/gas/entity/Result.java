package com.gas.entity;

/**
 * Created by GC on 2016/12/19.
 */
public class Result {
    //状态
    private boolean success;
    //消息
    private String message;
    //对象
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
