package com.gas.dao;

import com.gas.entity.YearlyInspection;

/**
 * Created by 刘维军 on 2017/01/18.
 */
public interface IYearlyInspectionDao {

    /**
     * 插入数据
     * */
    Integer add(YearlyInspection yearlyInspection);

    /**
     * 根据表号和年月日获取总数
     * */
    Integer getCountForYear(YearlyInspection yearlyInspection);

    /**
     * 更新数据
     * */
    Integer update(YearlyInspection yearlyInspection);
}
