package com.gas.service;

import com.gas.common.ehcache.EhcacheUtil;
import com.gas.common.security.Cryptos;
import com.gas.common.utils.IdGen;
import com.gas.dao.IUserMemberDao;
import com.gas.entity.Token;
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

    @Transactional(readOnly = false)
     public Token memberLogin(UserMember userMember){
         Token tocken=new Token();
            //查询密码
            UserMember um=dao.getUserForLoginName(userMember);
             if (um!=null){
                 tocken.setToken(IdGen.getuuid());
                 ehcacheUtil.put(tocken.getToken(),um);
             }else{
                 um= add(userMember);
                if (um!=null){
                    tocken.setToken(IdGen.getuuid());
                    ehcacheUtil.put(tocken.getToken(),um);
                }
             }
        return tocken;
     }

    /**
     * 添加用户
     * */
    @Transactional(readOnly = false)
    public UserMember add(UserMember member){
        UserMember um=dao.getUserForLoginName(member);
        if (um==null){
            member.preInsert();
            Integer count= dao.add(member);
            if (count>0){
                return member;
            }
        }
        return null;
    }

}
