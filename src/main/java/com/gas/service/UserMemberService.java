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

     public Token memberLogin(UserMember userMember){
         Token tocken=new Token();
            //查询密码
            UserMember um=dao.getUserForLoginName(userMember);
             if (um!=null){
                 //对比密码
                 if(um.getLoginPassword().equals(Cryptos.md5Encryption(userMember.getLoginPassword()))){
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
    public Integer add(UserMember member){
        member.preInsert();
        return  dao.add(member);
    }

}
