package com.gas.service;

import com.gas.common.ehcache.EhcacheUtil;
import com.gas.dao.IProtectionDao;
import com.gas.entity.Protection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by GC on 2016/12/21.
 */
@Service
@Transactional(readOnly = true)
public class ProtectionService {

    @Autowired
    IProtectionDao dao;



    /**
     * 查找所有的问题
     * */
    public List<Protection> getAll(){
        return  dao.getAll();
    }

}
