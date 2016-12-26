package com.gas.controller;

import com.gas.common.base.BaseController;
import com.gas.entity.ContentList;
import com.gas.entity.Gas;
import com.gas.entity.Result;
import com.gas.entity.UserMember;
import com.gas.service.GasService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 刘维军 on 2016/12/26.
 */
@Controller
@RequestMapping(value = "/gas")
public class GasController extends BaseController {
    @Autowired
    GasService service;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object getList(HttpServletRequest request,Gas gas){
        Result results=new Result();
        if (state){
            String page="1";
            if (StringUtils.isNumeric(request.getParameter("page"))){
                page=request.getParameter("page");
            }
            Integer pageNum=Integer.parseInt(page) ;
            PageHelper.startPage(pageNum,pageSize);
            gas.setUserId(userMember.getId());
            List<Gas> list=service.getAll(gas);
            PageInfo<Gas> pageInfo=new PageInfo<Gas>(list);
            ContentList<Gas> contentList=new ContentList<Gas>();
            contentList.setHasnext(pageInfo.isHasNextPage());
            contentList.setList(pageInfo.getList());
            results.setSuccess(true);
            results.setMessage("查询成功！");
            results.setData(contentList);
        }
        return results;
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Object add(Gas gas){
        Result results=new Result();
        if (state){
            gas.setUserId(userMember.getId());
            Integer count= service.add(gas);
            if (count>0){
                results.setSuccess(true);
                results.setMessage("添加成功！");
            } else {
                results.setSuccess(false);
                results.setMessage("添加失败！");
            }
        }
        return  results;
    }

}
