package com.gas.common;

/**
 * Created by 刘维军 on 2016/12/25.
 */
public enum SmsEnum {


    SUCCESS("验证成功",200),
    IS_NULL("AppKey为空",405),
    IS_INVALID("AppKey无效",406),
    IS_NULL_MOBILE("国家代码或手机号码为空",456),
    ERROR_MOBILE("手机号码格式错误",457),
    IS_NULL_VERIFICATIONCODE("请求校验的验证码为空",466),
    FREQUENTLY("请求校验验证码频繁",467),
    VERIFICATIONCODE_ERROR("验证码错误",468),
    SERVER_IS_OPEN("没有打开服务端验证开关",474);

    private String display;

    private int code;

    private SmsEnum(String display, int code) {
        this.display = display;
        this.code = code;
    }
    public int getCode() {
        return this.code;
    }

    public String getDisplay() {
        return this.display;
    }


    public static String getSexEnumByCode(Integer code){
        for(SmsEnum sexEnum : SmsEnum.values()){
            if(sexEnum.getCode()==code){
                return sexEnum.getDisplay();
            }
        }
        return null;
    }
}
