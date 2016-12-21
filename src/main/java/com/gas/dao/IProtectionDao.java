package com.gas.dao;

import com.gas.entity.Protection;

import java.util.List;

/**
 * Created by GC on 2016/12/21.
 */
public interface IProtectionDao {

    /**
     * 查找所有的问题
     * */
    List<Protection> getAll();

}
