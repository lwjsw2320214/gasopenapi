package com.gas.entity;


import com.gas.common.base.BaseEntity;

/**
 * Created by GC on 2016/12/26.
 */
public class Gas extends BaseEntity {

    private String gasNumber;
    private String userId;

    public String getGasNumber() {
        return gasNumber;
    }

    public void setGasNumber(String gasNumber) {
        this.gasNumber = gasNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
