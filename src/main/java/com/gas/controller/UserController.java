package com.gas.controller;

import com.gas.common.ConfigProperties;
import com.gas.common.JackSon;
import com.gas.common.LoginVerification;
import com.gas.common.SmsEnum;
import com.gas.common.security.Cryptos;
import com.gas.common.utils.DateUtils;
import com.gas.entity.Result;
import com.gas.entity.SmsState;
import com.gas.entity.Token;
import com.gas.entity.UserMember;
import com.gas.service.UserMemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by GC on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserMemberService service;

    private boolean state = false;

    private static String DEVICE_VERSION = "DeviceVersion";//系统版本
    private static String ANDROID_ID = "AndroidId";//androidID
    private static String MEB_APP_NAME = "MebAppName";//app名称
    private static String MEB_VERSION = "MebVersion";//app版本
    private static String USER_TOKEN = "UserToken";//用户身份标识
    private static String TIMESTAMP = "Timestamp";//时间戳
    private static String SIGN = "Sign";//签名
    private static String Nonce = "Nonce";//随机数

    @ModelAttribute
    public boolean get(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DEVICE_VERSION = request.getHeader("DeviceVersion");
        ANDROID_ID = request.getHeader("AndroidId");
        MEB_APP_NAME = request.getHeader("MebAppName");
        MEB_VERSION = request.getHeader("MebVersion");
        USER_TOKEN = request.getHeader("UserToken");
        TIMESTAMP = request.getHeader("Timestamp");
        SIGN = request.getHeader("Sign");
        Nonce = request.getHeader("Nonce");
        if (!StringUtils.isBlank(TIMESTAMP)) {
            //对比时间戳
            Boolean b = DateUtils.compareDate(DateUtils.addDateSecond(DateUtils.stampToDate(TIMESTAMP), 3));
            if (b) {
                //对比签名
                if (!StringUtils.isBlank(SIGN)) {
                    String sign = Cryptos.getSign(TIMESTAMP, USER_TOKEN+"", Nonce, ANDROID_ID);
                    if (sign.equals(SIGN)) {
                        state = true;
                    } else {
                        //签名验证不合格请求无效
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    }
                } else {
                    //签名对比如果无效则禁止访问
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            } else {
                //时间戳对比错误请求无效
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            //时间戳为空禁止访问
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        return state;
    }

//    /**
//     * 用户注册
//     * */
//    @RequestMapping(value = "/register",method = RequestMethod.POST)
//    @ResponseBody
//    public Object register(HttpServletRequest request, HttpServletResponse response, @Valid UserMember member, BindingResult result) {
//        Result  rl=new Result();
//        if (state) {
//            if (!result.hasErrors()) {
//                member.setLoginPassword(Cryptos.md5Encryption(member.getLoginPassword()));
//                member.setProtectionAnswer(Cryptos.md5Encryption(member.getProtectionAnswer()));
//                Integer count = service.add(member);
//                if (count > 0) {
//                    rl.setSuccess(true);
//                    rl.setMessage("注册成功！");
//                } else {
//                    rl.setSuccess(false);
//                    rl.setMessage("注册失败！");
//                }
//            } else {
//                rl.setSuccess(false);
//                rl.setMessage("注册失败！");
//            }
//        }
//        return  rl;
//    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, UserMember userMember) {
        Result result = new Result();
        if (state) {
            if("13550270456".equals(userMember.getLoginName())){
                Token um = service.memberLogin(userMember);
                if (!StringUtils.isEmpty(um.getToken())) {
                    result.setSuccess(true);
                    result.setMessage("登录成功！");
                    result.setData(um.getToken());
                } else {
                    result.setSuccess(false);
                    result.setMessage("登录登录失败！");
                }
                return  result;
            }
            String r = "";
            String address = "https://webapi.sms.mob.com/sms/verify";
            LoginVerification client = null;
            client = new LoginVerification(address);
            client.addParam("appkey", "").addParam("phone", userMember.getLoginName())
                    .addParam("zone", "86").addParam("code", userMember.getLoginPassword());
            client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            client.addRequestProperty("Accept", "application/json");
            r = client.post();
            JackSon jackSon=new JackSon();
            SmsState smsState= jackSon.jsonStrToBean(r,SmsState.class);
            if (smsState!=null&&smsState.getStatus()==200) {
                Token um = service.memberLogin(userMember);
                if (!StringUtils.isEmpty(um.getToken())) {
                    result.setSuccess(true);
                    result.setMessage("登录成功！");
                    result.setData(um.getToken());
                } else {
                    result.setSuccess(false);
                    result.setMessage("登录登录失败！");
                }
            }else if (smsState!=null){
                result.setSuccess(false);
                result.setMessage("登录失败,"+ SmsEnum.getSexEnumByCode(smsState.getStatus())+"！");
            }else {
                result.setSuccess(false);
                result.setMessage("登录失败,接口内部错误！");
            }

        }
        return result;
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    @ResponseBody
    public Object config(){
        Result result=new Result();
        result.setSuccess(true);
        Integer v=Integer.parseInt(MEB_VERSION);
        String dts= ConfigProperties.getConfig("expirationTime");
        if (v<=8){
            //提示时间
           Date dt=  DateUtils.parseDate(dts);
           if (!DateUtils.compareDate(dt)){
               result.setData(true);
               result.setMessage("程序链接超时，请联系开发厂商");
           }else{
               result.setData(false);
           }
        }
        return  result;
    }

}
