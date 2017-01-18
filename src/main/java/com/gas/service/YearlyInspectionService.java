package com.gas.service;

import com.gas.dao.IYearlyInspectionDao;
import com.gas.entity.YearlyInspection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 刘维军 on 2017/01/18.
 */
@Service
@Transactional(readOnly = true)
public class YearlyInspectionService {

    @Autowired
    IYearlyInspectionDao dao;

    /**
     * 插入数据
     * */
    @Transactional(readOnly = false)
    public Integer add(YearlyInspection yearlyInspection){
        Integer count=0;
        Integer c=getCountForYear(yearlyInspection);
        if (c>0){
           count= update(yearlyInspection);
        }else {
            yearlyInspection.preInsert();
            count=dao.add(yearlyInspection);
        }
        return count;
    }

    /**
     * 根据表号和年月日获取总数
     * */
    Integer getCountForYear(YearlyInspection yearlyInspection){
        return  dao.getCountForYear(yearlyInspection);
    }

    /**
     * 更新数据
     * */
    @Transactional(readOnly = false)
    Integer update(YearlyInspection yearlyInspection){
        return dao.update(yearlyInspection);
    }
}
