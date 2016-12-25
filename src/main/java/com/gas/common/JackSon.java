package com.gas.common;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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
    public <T> T jsonStrToBean(String jsonStr, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonStr, clazz);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
