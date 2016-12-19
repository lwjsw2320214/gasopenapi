package com.gas.entity;

/**
 * Created by GC on 2016/12/19.
 */
public class Result {
    //状态
    private boolean state;
    //消息
    private String message;
    //对象
    private Object data;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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
