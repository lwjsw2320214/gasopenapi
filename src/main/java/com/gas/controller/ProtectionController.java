package com.gas.controller;

import com.gas.common.base.BaseController;
import com.gas.entity.Protection;
import com.gas.entity.Result;
import com.gas.service.ProtectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by GC on 2016/12/21.
 */
@Controller
@RequestMapping(value = "/protection")
public class ProtectionController extends BaseController {

    @Autowired
    ProtectionService service;

    @RequestMapping
    @ResponseBody
    public Object index(){
        Result result=new Result();
        if (state) {
            List<Protection> list = service.getAll();
            result.setSuccess(true);
            result.setMessage("获取数据成功！");
            result.setData(list);
        }
        return  result;
    }

}
