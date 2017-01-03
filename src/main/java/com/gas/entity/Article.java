package com.gas.entity;

import com.gas.common.base.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * Created by GC on 2016/12/29.
 */
public class Article extends BaseEntity {

    @NotBlank(message = "新闻标题不能为空")
    private String title;
    @NotBlank(message = "略缩图不能为空")
    private String imges;
    @NotBlank(message = "文章简介不能为空")
    private String briefIntroduction;
    @NotBlank(message = "文章内容不能为空")
    private String content;
    private Integer newsType=1;
    private Date createTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImges() {
        return imges;
    }

    public void setImges(String imges) {
        this.imges = imges;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
