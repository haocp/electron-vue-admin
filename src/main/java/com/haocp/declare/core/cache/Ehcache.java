package com.haocp.declare.core.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: HPGT
 * @Date: 2020/12/22 16:05
 * @Description:
 */
public class Ehcache {

    private static final Logger log = LoggerFactory.getLogger(Ehcache.class);

    private static final String PATH = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "ehcache.xml";

    private static final String DEFAULT_CACHE_NAME = "userCache";

    private static Ehcache ehCache;

    private CacheManager manager;

    /**
     * 获得缓存配置管理
     * @param path
     */
    private Ehcache(String path) {
        try {
            manager = CacheManager.create(path);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取配置文件错误：{}",e.getMessage());
        }
    }

    /**
     * 初始化缓存管理类
     * @return
     */
    public static Ehcache getInstance() {
        try {
            if (ehCache== null) {
                ehCache= new Ehcache(PATH);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("初始化错误：{}",e.getMessage());
        }
        return ehCache;
    }

   /* static {
        CacheManager cacheManager = CacheManager.getCacheManager(DEFAULT_CACHE_NAME);
        if(cacheManager == null){
            try {
                cacheManager = CacheManager.create(PATH);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("获取配置文件错误：{}", e.getMessage());
            }
        }
        cache = cacheManager.getCache(DEFAULT_CACHE_NAME);
    }*/

    /**
     * 添加缓存数据
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        try {
            Element element = new Element(key, value);
            manager.getCache(DEFAULT_CACHE_NAME).put(element);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加缓存失败：{}", e.getMessage());
        }
    }

    /**
     * 获取缓存数据
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        try {
            Element element = manager.getCache(DEFAULT_CACHE_NAME).get(key);
            return element == null ? null : element.getObjectValue();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取缓存数据失败：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 模糊获取缓存数据key
     *
     * @param key 模糊字段
     * @return
     */
    public List<String> keys(String key) {
        List returns = new ArrayList<String>();
        try {
            List keys = manager.getCache(DEFAULT_CACHE_NAME).getKeys();
            for (int i = 0; i <keys.size() ; i++) {
                String keyp = (String)keys.get(i);
                if(keyp.contains(key)){
                    returns.add(keyp);
                }
            }
            return returns;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("模糊获取缓存数据失败：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除缓存数据
     *
     * @param keys
     */
    public void removeKeys(List keys) {
        try {
            for (int i = 0; i <keys.size() ; i++) {
                manager.getCache(DEFAULT_CACHE_NAME).remove(keys.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除缓存数据失败：{}", e.getMessage());
        }
    }

    /**
     * 删除缓存数据
     *
     * @param key
     */
    public void remove(String key) {
        try {
            manager.getCache(DEFAULT_CACHE_NAME).remove(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除缓存数据失败：{}", e.getMessage());
        }
    }

    public void update(String key,Object newValue) {
        try {
            remove(key);
            put(key,newValue);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新缓存数据失败：{}", e.getMessage());
        }
    }
}
