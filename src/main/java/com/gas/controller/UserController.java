package com.gas.controller;

import com.gas.entity.Result;
import com.gas.entity.UserManage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by GC on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    @ResponseBody
    public Object register(Model model, HttpServletRequest request, HttpServletResponse response)  throws  Exception {

        Result  result=new Result();
        int i=1;
        if (i==2){
            UserManage userManage=new UserManage();
            userManage.setId("1111");
            userManage.setUserName("username");
            userManage.setLoginToken("1111111111111");
            result.setState(true);
            result.setMessage("注册成功！");
            result.setData(userManage);
        }else{
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        return  result;
    }

}
