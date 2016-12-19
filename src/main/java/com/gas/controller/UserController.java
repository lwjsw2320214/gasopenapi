package com.gas.controller;

import com.gas.entity.Result;
import com.gas.entity.UserManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by GC on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    @ResponseBody
    public Object register(){
        UserManage userManage=new UserManage();
        userManage.setId("1111");
        userManage.setUserName("username");
        userManage.setLoginToken("1111111111111");
        Result  result=new Result();
        result.setState(true);
        result.setMessage("注册成功！");
        result.setData(userManage);
        return  result;
    }

}
