package com.gas.entity;

import java.util.List;

/**
 * Created by 刘维军 on 2016/12/26.
 */
public class ContentList<T> {
    private  boolean hasnext;
    private List<T> list;
    public boolean isHasnext() {
        return hasnext;
    }

    public void setHasnext(boolean hasnext) {
        this.hasnext = hasnext;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
