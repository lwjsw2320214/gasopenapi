package com.gas.dao;

import com.gas.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by GC on 2016/12/30.
 */
public interface IOrderDao {

    /**
     *根据用户id
     * 查询信息订单
     * */
    List<Order> getAll(String userId);

    /**
     * 根据id查询订单
     * */
    Order getOrder(@Param("id") String id,@Param("userid")String userid);

    /**
     * 获取指定表最新一条订单
     * */
    Order getLastOrder(String gasNumber);

    /**
     * 添加数据
     * */
    Integer add(Order order);
}
