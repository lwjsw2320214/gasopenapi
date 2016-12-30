package com.gas.service;

import com.gas.dao.ISettingPriceDao;
import com.gas.entity.SettingPrice;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by GC on 2016/12/27.
 */
@Service
@Transactional(readOnly = true)
public class SettingPriceService {

    @Autowired
    ISettingPriceDao dao;


    /**
     * 查询
     * */
    public SettingPrice getSettingPrice(){
        return  dao.getSettingPrice();
    }

    /**
     * 添加
     * */
    @Transactional(readOnly = false)
    public Integer add(SettingPrice price){
        price.preInsert();
        return  dao.add(price);
    }

    /**
     * 编辑
     * */
    @Transactional(readOnly = false)
    public Integer edit(SettingPrice price){
        Integer count=0;
        if (StringUtils.isBlank(price.getId())){
            count= add(price);
        }else {
            count= dao.edit(price);
        }
        return count;
    }

}
