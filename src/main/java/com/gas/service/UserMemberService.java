package com.gas.service;

import com.gas.common.ehcache.EhcacheUtil;
import com.gas.dao.IUserMemberDao;
import com.gas.entity.UserMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by GC on 2016/12/21.
 */
@Service
@Transactional(readOnly = true)
public class UserMemberService {

    @Autowired
    IUserMemberDao dao;

    EhcacheUtil ehcacheUtil=EhcacheUtil.getInstance();

     public UserMember memberLogin(UserMember userMember){
         UserMember um= (UserMember)ehcacheUtil.get(userMember.getLoginName());
         if (um==null){
            //查询密码
             um=dao.getUserForLoginName(userMember);
             if (um!=null){
                 ehcacheUtil.put(um.getLoginName(),um);
             }
         }
        return um;
     }

}
