package com.gas.entity;

import com.gas.common.base.BaseEntity;

/**
 * Created by GC on 2016/12/21.
 */
public class UserMember extends BaseEntity {
    private  String loginName;  //登录用户名
    private  String loginPassword;   //密码
    private  String protectionId;    //密保问题
    private String protectionAnswer;    //答案

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getProtectionId() {
        return protectionId;
    }

    public void setProtectionId(String protectionId) {
        this.protectionId = protectionId;
    }

    public String getProtectionAnswer() {
        return protectionAnswer;
    }

    public void setProtectionAnswer(String protectionAnswer) {
        this.protectionAnswer = protectionAnswer;
    }
}
