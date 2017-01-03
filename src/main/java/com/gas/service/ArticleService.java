package com.gas.service;

import com.gas.dao.IArticleDao;
import com.gas.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by GC on 2016/12/29.
 */
@Service
@Transactional(readOnly = true)
public class ArticleService {
    @Autowired
    IArticleDao dao;

    /**
     * 查询所有文章
     * */
    public List<Article> getAllList(Article article){
        return  dao.getAllList(article);
    }

    /**
     * 添加文章
     * */
    @Transactional(readOnly = false)
    public Integer add(Article article){
        article.preInsert();
        article.setCreateTime(new Date());
        return  dao.add(article);
    }


    /**
     * 根据id获取文章
     * */
    public Article getArticleForId(Article article){
        return  dao.getArticleForId(article);
    }

    /**
     * 修改文章
     * */
    @Transactional(readOnly = false)
    public Integer edit(Article article){
        return  dao.edit(article);
    }

    /**
     * 删除
     * */
    @Transactional(readOnly = false)
    public Integer delete(Article article){
        return  dao.delete(article);
    }
}
