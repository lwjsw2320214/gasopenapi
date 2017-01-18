package com.gas.entity;

import com.gas.common.base.BaseEntity;

import java.util.Date;

/**
 * Created by 刘维军 on 2017/01/18.
 */
public class YearlyInspection  extends BaseEntity {
    private Integer year;
    private String gasNumber;
    private String gasImg;
    private Date createTime;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGasNumber() {
        return gasNumber;
    }

    public void setGasNumber(String gasNumber) {
        this.gasNumber = gasNumber;
    }

    public String getGasImg() {
        return gasImg;
    }

    public void setGasImg(String gasImg) {
        this.gasImg = gasImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
