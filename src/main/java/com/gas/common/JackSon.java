package com.gas.common;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by GC on 2016/12/19.
 */
public class JackSon {
    /**
     * Json字符串转对象
     * @param <T>
     * @param jsonStr
     * @param clazz
     * @return
     * @throws Exception
     */
    public <T> T jsonStrToBean(String jsonStr, Class<T> clazz) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonStr, clazz);
    }

    /**
     * 对象转Json字符串
     * @param bean
     * @return
     * @throws Exception
     */
    public String beanToJsonStr(Object bean){
        try{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(bean);
        } catch(Exception exception){
            exception.printStackTrace();
            return  null;
        }
    }
}
