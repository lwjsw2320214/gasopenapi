package com.gas.controller;

import com.gas.common.base.BaseController;
import com.gas.common.utils.DateUtils;
import com.gas.entity.ContentList;
import com.gas.entity.Order;
import com.gas.entity.Result;
import com.gas.entity.SettingPrice;
import com.gas.service.OrderService;
import com.gas.service.SettingPriceService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by GC on 2016/12/30.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {
    @Autowired
    OrderService service;
    @Autowired
    SettingPriceService settingPriceService;


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object getAll(HttpServletRequest request){
        Result result=new Result();
        String page="1";
        if (StringUtils.isNumeric(request.getParameter("page"))){
            page=request.getParameter("page");
        }
        Integer pageNum=Integer.parseInt(page) ;
        PageHelper.startPage(pageNum,pageSize);
        ContentList<Order> contentList=new ContentList<Order>();
        List<Order>list= service.getAll(userMember.getId());
        contentList.setHasnext(true);
        contentList.setList(list);
        result.setSuccess(true);
        result.setMessage("查询成功");
        result.setData(contentList);
        return result;
    }

    @RequestMapping(value = "/getOrder",method = RequestMethod.GET)
    @ResponseBody
    public Object getOrder(String id){
        Result result=new Result();
        Order order= service.getOrder(id,userMember.getId());
        result.setSuccess(true);
        result.setMessage("查询成功");
        result.setData(order);
        return  result;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(Order order){
        Integer count=0;
        Result result=new Result();
        SettingPrice settingPrice= settingPriceService.getSettingPrice();
        Order od= service.getLastOrder(order.getGasNumber());
        //如果是第一次录入则直接作为开始度数不产生金钱
        if(od==null){
            Date date=new Date();
            String year= DateUtils.getYear();
            String moth=DateUtils.getMonth();
            //订单号
            order.setOrderNumber(order.getGasNumber()+year+moth);
            //开始度数
            order.setFirstScale(order.getLastScale());
            //使用度数
            order.setTotal(order.getLastScale()-order.getFirstScale());
            //单价
            order.setUnitPrice(settingPrice.getPrice());
            //总价
            order.setPaymentAmount( settingPrice.getPrice().multiply( new BigDecimal(order.getTotal())));
            //开始时间
            order.setFirstTime(date);
            //结束时间
            order.setCreateTime(date);
            //订单是否支付
            order.setPaymentState(1);
            count=service.add(order);
        }else {
            Date date=new Date();
            String year= DateUtils.getYear();
            String moth=DateUtils.getMonth();
            //订单号
            order.setOrderNumber(order.getGasNumber()+year+moth);
            //开始度数
            order.setFirstScale(od.getLastScale());
            //使用度数
            order.setTotal(order.getLastScale()-order.getFirstScale());
            //单价
            order.setUnitPrice(settingPrice.getPrice());
            //总价
            order.setPaymentAmount( settingPrice.getPrice().multiply( new BigDecimal(order.getTotal())));
            //开始时间
            order.setFirstTime(od.getCreateTime());
            //结束时间
            order.setCreateTime(date);
            order.setPaymentState(0);
            //判断该订单是否添加和使用度数是否大于0
            if(!order.getOrderNumber().equals(od.getOrderNumber())&& order.getTotal()>0){
                count=service.add(order);
            }
        }
        if (count>0){
            result.setSuccess(true);
            result.setMessage("添加成功");
        }else{
            result.setSuccess(false);
            result.setMessage("添加失败，请查看数据是否正确或者该订单是否已经提交");
        }
        return  result;
    }

}
