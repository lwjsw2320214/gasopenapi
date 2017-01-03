package com.gas.service;

import com.gas.dao.IGasDao;
import com.gas.entity.Gas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 刘维军 on 2016/12/26.
 */
@Service
@Transactional(readOnly = true)
public class GasService {

    @Autowired
    IGasDao dao;

    /**
     * 根据条件查询
     * 所有数据
     * */
    public List<Gas> getAll(Gas gas){
        return  dao.getAll(gas);
    }

    /**
     * 添加
     * */

    @Transactional(readOnly = false)
    public Integer add(Gas gas){
        Integer size=getGasCountForNumberAndUser(gas);
        Integer count=0;
        if (size<=0){
            gas.preInsert();
            count= dao.add(gas);
        }
        return count;
    }

    /**
     * 根据用户id和煤气表
     * 号码获取数据
     * */
    public Integer getGasCountForNumberAndUser(Gas gas){
        try{
            return  dao.getGasCountForNumberAndUser(gas);
        } catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 删除
     * */
    @Transactional(readOnly = false)
    public Integer delete(Gas gas){
        return  dao.delete(gas);
    }
}
