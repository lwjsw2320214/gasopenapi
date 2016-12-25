package com.gas.common.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.net.URL;

/**
 * Created by 刘维军 on 2016/12/25.
 */
public class UserEhcacheUtil {

    private static final String path = "/ehcache.xml";
    private static  final String cacheName="verificationCodeCache";

    private URL url;

    private CacheManager manager;

    private static UserEhcacheUtil ehCache;

    private UserEhcacheUtil(String path) {
        url = getClass().getResource(path);
        manager = CacheManager.create(url);
    }

    public static UserEhcacheUtil getInstance() {
        if (ehCache== null) {
            ehCache= new UserEhcacheUtil(path);
        }
        return ehCache;
    }

    public void put( String key, Object value) {
        Cache cache = manager.getCache(cacheName);
        Element element = new Element(key, value);
        cache.put(element);
    }

    public Object get(String key) {
        Cache cache = manager.getCache(cacheName);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public Cache getCache(String cacheName) {
        return manager.getCache(cacheName);
    }

    public void remove(String key) {
        Cache cache = manager.getCache(cacheName);
        cache.remove(key);
    }

}
