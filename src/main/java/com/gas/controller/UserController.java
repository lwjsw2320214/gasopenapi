package com.gas.controller;

import com.gas.entity.Result;
import com.gas.entity.UserMember;
import com.gas.service.UserMemberService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    UserMemberService service;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    @ResponseBody
    public Object register(Model model, HttpServletRequest request, HttpServletResponse response)  throws  Exception {

        long seconds =System.currentTimeMillis();
        System.out.println(seconds);
        Result  result=new Result();

        return  result;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public  Object login(HttpServletRequest request,HttpServletResponse response,UserMember userMember){
        Result result=new Result();
        UserMember um=  service.memberLogin(userMember);
        result.setSuccess(true);
        result.setMessage("登录成功！");
        result.setData(um);
        return  result;
    }

}
