package com.gas.dao;


import com.gas.entity.Article;

import java.util.List;

/**
 * Created by GC on 2016/12/29.
 */
public interface IArticleDao {

    /**
     * 查询所有文章
     * */
     List<Article> getAllList(Article article);

     /**
      * 添加文章
      * */
     Integer add(Article article);

     /**
      * 根据id获取文章
      * */
     Article getArticleForId(Article article);


     /**
      * 修改文章
      * */
     Integer edit(Article article);

     /**
      * 删除
      * */
     Integer delete(Article article);

}
