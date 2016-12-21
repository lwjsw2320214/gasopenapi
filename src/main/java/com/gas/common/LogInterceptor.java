package com.gas.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * 重写拦截器，记录日志
 * Created by GC on 2016/11/08.
 */
public class LogInterceptor implements HandlerInterceptor {

    /**
     * 日志对象1
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView!=null){
            long starttime=System.currentTimeMillis();
            logger.debug(" 访问时间：{}",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").format(starttime));
            logger.debug("ViewName:{}",modelAndView.getViewName());
        }
    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
