package com.gas.common;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘维军 on 2016/12/26.
 */
public class ConfigProperties {
    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<String, String>();
    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("config.properties");

    /**
     * 获取配置
     * @see ${fns:getConfig('adminPath')}
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null){
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }
}
