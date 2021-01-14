package com.haocp.declare.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @Auther: HPGT
 * @Date: 2021/1/12 18:23
 * @Description:
 */

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));     // 缓存信息配置文件
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }
}
