package com.gas.service;

import com.gas.dao.IOrderDao;
import com.gas.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by GC on 2016/12/30.
 */
@Service
@Transactional(readOnly = true)
public class OrderService {

    @Autowired
    IOrderDao dao;

    /**
     *根据用户id
     * 查询信息订单
     * */
    public List<Order> getAll(String userId){
        return  dao.getAll(userId);
    }

    /**
     * 根据id查询订单
     * */
    public Order getOrder(String id,String userid){
        return  dao.getOrder(id,userid);
    }

    /**
     * 获取指定表最新一条订单
     * */
    public Order getLastOrder(String gasNumber){
        return  dao.getLastOrder(gasNumber);
    }

    /**
     * 添加数据
     * */
    @Transactional(readOnly = false)
    public Integer add(Order order){
        order.preInsert();
        return  dao.add(order);
    }
}
