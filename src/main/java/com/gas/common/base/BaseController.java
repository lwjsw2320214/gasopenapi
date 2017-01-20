package com.gas.common.base;

import com.gas.common.ConfigProperties;
import com.gas.common.ehcache.EhcacheUtil;
import com.gas.common.security.Cryptos;
import com.gas.common.utils.DateUtils;
import com.gas.entity.UserMember;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by 刘维军 on 2016/12/26.
 */
public class BaseController {
    protected   Integer pageSize= Integer.parseInt(ConfigProperties.getConfig("pageSize"));
    protected EhcacheUtil ehcacheUtil=EhcacheUtil.getInstance();
    protected boolean state = false;
    protected UserMember userMember=new UserMember();
    protected static String DEVICE_VERSION = "DeviceVersion";//系统版本
    protected static String ANDROID_ID = "AndroidId";//androidID
    protected static String MEB_APP_NAME = "MebAppName";//app名称
    protected static String MEB_VERSION = "MebVersion";//app版本
    protected static String USER_TOKEN = "UserToken";//用户身份标识
    protected static String TIMESTAMP = "Timestamp";//时间戳
    protected static String SIGN = "Sign";//签名
    protected static String Nonce = "Nonce";//随机数

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
            Boolean b = DateUtils.compareDate(DateUtils.addDateSecond(DateUtils.stampToDate(TIMESTAMP), 60));
            if (b) {
                //对比签名
                if (!StringUtils.isBlank(SIGN)) {
                    String sign = Cryptos.getSign(TIMESTAMP, USER_TOKEN+"", Nonce, ANDROID_ID);
                    if (sign.equals(SIGN)) {
                        Integer v=Integer.parseInt(MEB_VERSION);
                            //提示时间
                            String dts= ConfigProperties.getConfig("expirationTime");
                            Date dt=  DateUtils.parseDate(dts);
                            if (!DateUtils.compareDate(dt)){
                                //获取用户失败返回403
                                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                            }else {
                        //判断令牌是否能获取到用户
                        Object object=ehcacheUtil.get(USER_TOKEN);
                       if (object!=null){
                           userMember= (UserMember)object;
                            state = true;
                       }else{
                            //获取用户失败返回403
                           response.sendError(HttpServletResponse.SC_FORBIDDEN);
                       }}
                    } else {
                        //签名验证不合格请求无效400
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    }
                } else {
                    //签名对比如果无效则禁止访问403
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            } else {
                //时间戳对比错误请求无效400
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            //时间戳为空禁止访问403
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
        return state;
    }

}
