package com.gas.dao;


import com.gas.entity.Gas;

import java.util.List;

/**
 * Created by GC on 2016/12/26.
 */
public interface IGasDao {

    /**
     * 根据条件查询
     * 所有数据
     * */
    List<Gas> getAll(Gas gas);

    /**
     * 添加
     * */
    Integer add(Gas gas);

    /**
     * 根据用户id和煤气表
     * 号码获取数据
     * */
    Integer getGasCountForNumberAndUser(Gas gas);

    /**
     * 删除
     * */
    Integer delete(Gas gas);

}
