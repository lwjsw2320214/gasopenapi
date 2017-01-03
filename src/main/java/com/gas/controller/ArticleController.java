package com.gas.controller;

import com.gas.common.base.BaseController;
import com.gas.entity.Article;
import com.gas.entity.ContentList;
import com.gas.entity.Gas;
import com.gas.entity.Result;
import com.gas.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.nativejdbc.OracleJdbc4NativeJdbcExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 刘维军 on 2017/01/01.
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {
    @Autowired
    ArticleService service;

    @RequestMapping(value = "/newsList", method = RequestMethod.GET)
    @ResponseBody
    public Object newsList(HttpServletRequest request, Article article) {
        Result result = new Result();
        if (state) {
            String page = "1";
            if (StringUtils.isNumeric(request.getParameter("page"))) {
                page = request.getParameter("page");
            }
            Integer pageNum = Integer.parseInt(page);
            PageHelper.startPage(pageNum, pageSize);
            article.setNewsType(1);
            List<Article> list = service.getAllList(article);
            PageInfo<Article> pageInfo = new PageInfo<Article>(list);
            ContentList<Article> contentList = new ContentList<Article>();
            contentList.setHasnext(pageInfo.isHasNextPage());
            contentList.setList(pageInfo.getList());
            result.setSuccess(true);
            result.setMessage("查询成功！");
            result.setData(contentList);
        }
        return result;
    }

    @RequestMapping(value = "/newsInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object newsInfo(Article article) {
        Result result = new Result();
        if (state) {
            article.setNewsType(1);
            article = service.getArticleForId(article);
            result.setData(article);
            result.setMessage("查询成功");
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    @ResponseBody
    public Object notice(HttpServletRequest request, Article article) {
        Result result = new Result();
        if (state) {
            String page = "1";
            if (StringUtils.isNumeric(request.getParameter("page"))) {
                page = request.getParameter("page");
            }
            Integer pageNum = Integer.parseInt(page);
            PageHelper.startPage(pageNum, pageSize);
            article.setNewsType(2);
            List<Article> list = service.getAllList(article);
            PageInfo<Article> pageInfo = new PageInfo<Article>(list);
            ContentList<Article> contentList = new ContentList<Article>();
            contentList.setHasnext(pageInfo.isHasNextPage());
            contentList.setList(pageInfo.getList());
            result.setSuccess(true);
            result.setMessage("查询成功！");
            result.setData(contentList);
        }
        return result;
    }

    @RequestMapping(value = "/noticeInfo", method = RequestMethod.GET)
    @ResponseBody
    public Object noticeInfo(Article article) {
        Result result = new Result();
        if (state) {
            article.setNewsType(2);
            article = service.getArticleForId(article);
            result.setData(article);
            result.setMessage("查询成功");
            result.setSuccess(true);
        }
        return result;
    }

}
