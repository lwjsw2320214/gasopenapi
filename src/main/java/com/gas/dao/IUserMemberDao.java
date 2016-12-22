package com.gas.dao;

import com.gas.entity.UserMember;

/**
 * Created by GC on 2016/12/21.
 */
public interface IUserMemberDao {

    /**
     * 根据用户名查找用户
     * */
    UserMember getUserForLoginName(UserMember userMember);
    /**
     * 添加用户
     * */
    Integer add(UserMember member);

}
