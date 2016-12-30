package com.gas.dao;


import com.gas.entity.SettingPrice;

/**
 * Created by GC on 2016/12/27.
 */
public interface ISettingPriceDao {

    /**
     * 查询
     * */
    SettingPrice getSettingPrice();

    /**
     * 添加
     * */
    Integer add(SettingPrice price);

    /**
     * 编辑
     * */
    Integer edit(SettingPrice price);
}
